/*******************************************************************************
 * Subdivision.java
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
 * Contains data for the subdivisions associated with an IP address.
 *
 * This record is returned by all the end points except the Country end point.
 */
final public class Subdivision extends AbstractNamedRecord {
    @JsonProperty
    private Integer confidence;

    @JsonProperty("iso_code")
    private String isoCode;

    public Subdivision() {
    }

    /**
     * @return This is a value from 0-100 indicating MaxMind's confidence that
     *         the subdivision is correct. This attribute is only available from
     *         the Omni end point.
     */
    public Integer getConfidence() {
        return this.confidence;
    }

    /**
     * @return This is a string up to three characters long contain the
     *         subdivision portion of the <a
     *         href="http://en.wikipedia.org/wiki/ISO_3166-2 ISO 3166-2"
     *         >code</a>. This attribute is returned by all end points except
     *         Country.
     */
    public String getIsoCode() {
        return this.isoCode;
    }
}
