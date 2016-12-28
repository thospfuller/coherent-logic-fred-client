package com.coherentlogic.fred.client.core.domain;

/**
 * An optional parameter that indicates a lower frequency to aggregate values
 * to.
 * <p>
 * Read the 'Frequency Aggregation' section of the FRED FAQs for implementation
 * details.
 * <p>
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_observations.html#frequency">frequency</a>
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public enum Frequency {

    /** daily */
    d,
    /** weekly */
    w,
    /** bi-weekly */
    bw,
    /** monthly */
    m,
    /** quarterly */
    q,
    /** semiAnnually */
    sa,
    /** annually */
    a,
    /** weeklyEndingFriday */
    wef,
    /** weeklyEndingThursday */
    weth,
    /** weeklyEndingWednesday */
    wew,
    /** weeklyEndingTuesday */
    wetu,
    /** weeklyEndingMonday */
    wem,
    /** biWeeklyEndingWednesday */
    bwew,
    /** biWeeklyEndingMonday */
    bwem,
    /** 5y */
    _5y,
    /** n/a */
    na;
 
}
