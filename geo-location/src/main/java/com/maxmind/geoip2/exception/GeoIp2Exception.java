/*******************************************************************************
 * GeoIp2Exception.java
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
package com.maxmind.geoip2.exception;

/**
 * This class represents a generic GeoIP2 error. All other exceptions thrown by
 * the GeoIP2 API subclass this exception
 */
public class GeoIp2Exception extends Exception {

    private static final long serialVersionUID = -1923104535309628719L;

    /**
     * @param message
     *            A message describing the reason why the exception was thrown.
     */
    public GeoIp2Exception(String message) {
        super(message);
    }

    /**
     * @param message
     *            A message describing the reason why the exception was thrown.
     * @param cause
     *            The cause of the exception.
     */
    public GeoIp2Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
