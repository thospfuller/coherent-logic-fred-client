package com.coherentlogic.geofred.client.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_group.html">GeoFRED API - Series Group Info</a>
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesGroups {

    private List<SeriesGroup> seriesGroupList;

    public SeriesGroups() {
        this (new ArrayList<SeriesGroup> ());
    }

    public SeriesGroups(List<SeriesGroup> shapeList) {
        this.seriesGroupList = shapeList;
    }

    public List<SeriesGroup> getSeriesGroupList() {
        return seriesGroupList;
    }

    public void setSeriesGroupList(List<SeriesGroup> seriesGroupList) {
        this.seriesGroupList = seriesGroupList;
    }

    public void addSeriesGroup(SeriesGroup... seriesGroups) {
        seriesGroupList.addAll(Arrays.asList(seriesGroups));
    }

    @Override
    public String toString() {
        return "SeriesGroups [seriesGroupList=" + seriesGroupList + "]";
    }
}
