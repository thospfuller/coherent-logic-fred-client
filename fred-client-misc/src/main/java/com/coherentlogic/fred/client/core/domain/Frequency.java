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
    /** weeklyEndingSunday */
    wesu,
    /** weeklyEndingSaturday */
    wesa,
    /** biWeeklyEndingWednesday */
    bwew,
    /** biWeeklyEndingMonday */
    bwem,
    /**
     * 5y
     *
     * @deprecated This value does not appear in the list of Frequencies and will be removed in the next iteration.
     */
    _5y,
    /**
     * n/a
     *
     * @deprecated This value does not appear in the list of Frequencies and will be removed in the next iteration.
     */
    na;
}
