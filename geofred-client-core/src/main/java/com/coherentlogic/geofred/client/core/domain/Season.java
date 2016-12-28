package com.coherentlogic.geofred.client.core.domain;

/**
 * The seasonality of the series group.
 *
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#season">Season</a>
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public enum Season {

    /**
     * Seasonally adjusted
     */
    SA,

    /**
     * Not seasonally adjusted
     */
    NSA;
}
