/*******************************************************************************
 * CassandraConnectionProvider.java
 * insights-event-logger
 * Created by Gooru on 2014
 * Copyright (c) 2014 Gooru. All rights reserved.
 * http://www.goorulearning.org/
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kafka.event.microaggregator.core;

import com.netflix.astyanax.AstyanaxContext;
import com.netflix.astyanax.Keyspace;
import com.netflix.astyanax.connectionpool.NodeDiscoveryType;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolConfigurationImpl;
import com.netflix.astyanax.connectionpool.impl.ConnectionPoolType;
import com.netflix.astyanax.connectionpool.impl.CountingConnectionPoolMonitor;
import com.netflix.astyanax.connectionpool.impl.SmaLatencyScoreStrategyImpl;
import com.netflix.astyanax.impl.AstyanaxConfigurationImpl;
import com.netflix.astyanax.thrift.ThriftFamilyFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CassandraConnectionProvider {

    private Properties properties;
    private Keyspace cassandraKeyspace;
    private static final Logger logger = LoggerFactory.getLogger(CassandraConnectionProvider.class);
    private static String CASSANDRA_IP;
    private static String CASSANDRA_PORT;
    private static String CASSANDRA_KEYSPACE;

    public void init(Map<String, String> configOptionsMap) {

        properties = new Properties();
        CASSANDRA_IP = System.getenv("INSIGHTS_CASSANDRA_IP");
        CASSANDRA_PORT = System.getenv("INSIGHTS_CASSANDRA_PORT");
        CASSANDRA_KEYSPACE = System.getenv("INSIGHTS_CASSANDRA_KEYSPACE");

        try {

            logger.info("Loading cassandra properties");
            String hosts = CASSANDRA_IP;
            String keyspace = CASSANDRA_KEYSPACE;

            if (configOptionsMap != null) {
                hosts = StringUtils.defaultIfEmpty(
                        configOptionsMap.get("hosts"), hosts);
                keyspace = StringUtils.defaultIfEmpty(
                        configOptionsMap.get("keyspace"), keyspace);
            }

            properties.load(MicroAggregationLoader.class
                    .getResourceAsStream("/loader.properties"));
            String clusterName = properties.getProperty("cluster.name",
                    "gooruinsights");

            ConnectionPoolConfigurationImpl poolConfig = new ConnectionPoolConfigurationImpl("MyConnectionPool")
                    .setPort(9160)
                    .setMaxConnsPerHost(3)
                    .setSeeds(hosts)
		    .setLatencyAwareUpdateInterval(10000)
                    .setLatencyAwareResetInterval(0)
                    .setLatencyAwareBadnessThreshold(2)
                    .setLatencyAwareWindowSize(100)
		;
            if (!hosts.startsWith("127.0")) {
                poolConfig.setLocalDatacenter("datacenter1");
            }

            poolConfig.setLatencyScoreStrategy(new SmaLatencyScoreStrategyImpl()); // Enabled SMA.  Omit this to use round robin with a token range

            AstyanaxContext<Keyspace> context = new AstyanaxContext.Builder()
                    .forCluster(clusterName)
                    .forKeyspace(keyspace)
                    .withAstyanaxConfiguration(new AstyanaxConfigurationImpl()
                    .setDiscoveryType(NodeDiscoveryType.RING_DESCRIBE)
                    .setConnectionPoolType(ConnectionPoolType.TOKEN_AWARE))
                    .withConnectionPoolConfiguration(poolConfig)
                    .withConnectionPoolMonitor(new CountingConnectionPoolMonitor())
                    .buildKeyspace(ThriftFamilyFactory.getInstance());

            context.start();

            cassandraKeyspace = (Keyspace) context.getClient();
            logger.info("Initialized connection to Cassandra");
        } catch (IOException e) {
            logger.info("Error while initializing cassandra", e);
        }
    }

    public Keyspace getKeyspace() throws IOException {
        if (cassandraKeyspace == null) {
            throw new IOException("Keyspace not initialized.");
        }
        return cassandraKeyspace;
    }
}
