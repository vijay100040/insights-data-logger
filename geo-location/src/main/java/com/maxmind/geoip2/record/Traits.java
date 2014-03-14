/*******************************************************************************
 * Traits.java
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
/**
 * This class provides a model for the data returned by the GeoIP2 Omni end
 * point.
 *
 * The only difference between the City, City/ISP/Org, and Omni model classes is
 * which fields in each record may be populated.
 *
 * @see <a href="http://dev.maxmind.com/geoip/geoip2/web-services">GeoIP2 Web
 *      Services</a>
 */
package com.maxmind.geoip2.record;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains data for the traits record associated with an IP address.
 *
 * This record is returned by all the end points.
 */
final public class Traits {
    @JsonProperty("autonomous_system_number")
    private Integer autonomousSystemNumber;

    @JsonProperty("autonomous_system_organization")
    private String autonomousSystemOrganization;

    @JsonProperty
    private String domain;

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("is_anonymous_proxy")
    private boolean anonymousProxy;

    @JsonProperty("is_satellite_provider")
    private boolean satelliteProvider;

    @JsonProperty
    private String isp;

    @JsonProperty
    private String organization;

    @JsonProperty("user_type")
    private String userType;

    public Traits() {
        // Empty traits object
    }

    /**
     * @return The <a
     *         href="http://en.wikipedia.org/wiki/Autonomous_system_(Internet)"
     *         >autonomous system number</a> associated with the IP address.
     *         This attribute is only available from the City/ISP/Org and Omni
     *         end points.
     */
    public Integer getAutonomousSystemNumber() {
        return this.autonomousSystemNumber;
    }

    /**
     * @return The organization associated with the registered <a
     *         href="http://en.wikipedia.org/wiki/Autonomous_system_(Internet)"
     *         >autonomous system number</a> for the IP address. This attribute
     *         is only available from the City/ISP/Org and Omni end points.
     */
    public String getAutonomousSystemOrganization() {
        return this.autonomousSystemOrganization;
    }

    /**
     * @return The second level domain associated with the IP address. This will
     *         be something like "example.com" or "example.co.uk", not
     *         "foo.example.com". This attribute is only available from the
     *         City/ISP/Org and Omni end points.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * @return The IP address that the data in the model is for. If you
     *         performed a "me" lookup against the web service, this will be the
     *         externally routable IP address for the system the code is running
     *         on. If the system is behind a NAT, this may differ from the IP
     *         address locally assigned to it. This attribute is returned by all
     *         end points.
     */
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**
     * @return The name of the ISP associated with the IP address. This
     *         attribute is only available from the City/ISP/Org and Omni end
     *         points.
     */
    public String getIsp() {
        return this.isp;
    }

    /**
     * @return The name of the organization associated with the IP address. This
     *         attribute is only available from the City/ISP/Org and Omni end
     *         points.
     *
     */
    public String getOrganization() {
        return this.organization;
    }

    /**
     * @return <p>
     *         The user type associated with the IP address. This can be one of
     *         the following values:
     *         </p>
     *         <ul>
     *         <li>business
     *         <li>cafe
     *         <li>cellular
     *         <li>college
     *         <li>content_delivery_network
     *         <li>dialup
     *         <li>government
     *         <li>hosting
     *         <li>library
     *         <li>military
     *         <li>residential
     *         <li>router
     *         <li>school
     *         <li>search_engine_spider
     *         <li>traveler
     *         </ul>
     *         <p>
     *         This attribute is only available from the Omni end point.
     *         </p>
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * @return This is true if the IP is an anonymous proxy. This attribute is
     *         returned by all end points.
     * @see <a href="http://dev.maxmind.com/faq/geoip#anonproxy">MaxMind's GeoIP
     *      FAQ</a>
     */
    public boolean isAnonymousProxy() {
        return this.anonymousProxy;
    }

    /**
     * @return This is true if the IP belong to a satellite internet provider.
     *         This attribute is returned by all end points.
     */
    public boolean isSatelliteProvider() {
        return this.satelliteProvider;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Traits ["
                + (this.autonomousSystemNumber != null ? "autonomousSystemNumber="
                        + this.autonomousSystemNumber + ", "
                        : "")
                + (this.autonomousSystemOrganization != null ? "autonomousSystemOrganization="
                        + this.autonomousSystemOrganization + ", "
                        : "")
                + (this.domain != null ? "domain=" + this.domain + ", " : "")
                + (this.ipAddress != null ? "ipAddress=" + this.ipAddress
                        + ", " : "")
                + "anonymousProxy="
                + this.anonymousProxy
                + ", satelliteProvider="
                + this.satelliteProvider
                + ", "
                + (this.isp != null ? "isp=" + this.isp + ", " : "")
                + (this.organization != null ? "organization="
                        + this.organization + ", " : "")
                + (this.userType != null ? "userType=" + this.userType : "")
                + "]";
    }
}
