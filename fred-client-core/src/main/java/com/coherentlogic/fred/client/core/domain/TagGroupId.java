package com.coherentlogic.fred.client.core.domain;

/**
 * This enumeration contains the tag group identifiers available.
 *
 * @see <a href="https://api.stlouisfed.org/docs/fred/series_search_tags.html#tag_group_id">Tag group id</a>
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public enum TagGroupId {

    /**
     * Frequency
     */
    freq,

    /**
     * General or Concept
     */
    gen,

    /**
     * Geography
     */
    geo,

    /**
     * Geography Type
     */
    geot,

    /**
     * Release
     */
    rls,

    /**
     * Seasonal Adjustment
     */
    seas,

    /**
     * Source
     */
    src
}
