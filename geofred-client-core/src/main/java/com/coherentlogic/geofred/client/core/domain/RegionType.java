package com.coherentlogic.geofred.client.core.domain;

/**
 * Region types available for regional data.
 *
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">region_type</a>
 */
public enum RegionType {

    /**
     * 
     */
    bea,
    /**
     * 
     */
    msa,
    /**
     * 
     */
    frb,
    /**
     * 
     */
    necta,
    /**
     * 
     */
    state,
    /**
     * 
     */
    country,
    /**
     * 
     */
    county,
    /**
     * 
     */
    censusregion,
    /**
     * Not sure about this value as the documentation has censusregion listed twice and the shape has this value (in
     * fact the available shapes are, aside from this value, the same as the region types).
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html#shape">Shape</a>
     */
    censusdivision;
}
