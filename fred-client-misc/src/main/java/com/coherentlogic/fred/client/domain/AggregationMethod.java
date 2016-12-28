package com.coherentlogic.fred.client.domain;

/**
 * A key that indicates the aggregation method used for frequency aggregation.
 * This parameter has no affect if the frequency parameter is not set.
 *
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_observations.html#aggregation_method">Aggregation Method</a>
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#aggregation_method">aggregation_method</a>
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public enum AggregationMethod {

    /**
     * Average (the default)
     */
    avg,

    /**
     * Sum
     */
    sum,

    /**
     * End of period
     */
    eop;
}
