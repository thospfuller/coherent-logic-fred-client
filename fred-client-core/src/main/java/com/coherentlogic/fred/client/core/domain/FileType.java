package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.Constants.FILE_TYPE;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * An enumeration that describes the file types available.
 *
 * @see com.coherentlogic.fred.client.core.domain.Message
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_observations.html">
 * series_observations</a>
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@XStreamAlias(FILE_TYPE)
public enum FileType {
    xml,
    txt,
    xls,
    /**
     * JSON is only available with GeoFRED queries.
     */
    json;
}
