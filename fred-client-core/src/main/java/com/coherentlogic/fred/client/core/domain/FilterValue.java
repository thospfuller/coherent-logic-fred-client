package com.coherentlogic.fred.client.core.domain;

/**
 * An enumeration of filter values.
 * <p>
 * Limit results by geographic type of economic data series; namely 'macro',
 * 'regional', and 'all'.
 * <p>
 * Note that 'macro' limits results to macroeconomic data series. In other
 * words, series representing the entire United States. 'regional' limits
 * results to series for parts of the US; namely, series for US states,
 * counties, and Metropolitan Statistical Areas (MSA).
 * <p>
 * Note that 'all' does not filter results.
 * <p>
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_updates.html#filter_value">
 *  filter_value</a>
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_search.html">
 *       series_search</a>
 * @see <a href="https://api.stlouisfed.org/docs/fred/release_series.html">
 *       release_series</a>
 * @see <a href="https://api.stlouisfed.org/docs/fred/category_series.html">
 *       category_series</a>
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public enum FilterValue {

    all, macro, regional;

}
