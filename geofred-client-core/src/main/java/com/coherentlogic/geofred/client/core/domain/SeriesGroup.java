package com.coherentlogic.geofred.client.core.domain;

import java.util.Date;

import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_group.html">GeoFRED API - Series Group Info</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesGroup extends SerializableBean<SeriesGroup> {

    private static final long serialVersionUID = -7833634069744252915L;

    public static final String
        TITLE = "title",
        REGION_TYPE = "region_type",
        SERIES_GROUP = "series_group",
        SEASON = "season",
        UNITS = "units",
        FREQUENCY = "frequency",
        MIN_DATE = "min_date",
        MAX_DATE = "max_date";

    public static final String
        REGIONTYPE = "regionType",
        SERIESGROUP = "seriesGroup",
        MINDATE = "minDate",
        MAXDATE = "maxDate";

    private String title;

    private String regionType;

    private String seriesGroup;

    private String season;

    private String units;

    private String frequency;

    private Date minDate;

    private Date maxDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(@Changeable(TITLE) String title) {
        this.title = title;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(@Changeable(REGIONTYPE) String regionType) {
        this.regionType = regionType;
    }

    public String getSeriesGroup() {
        return seriesGroup;
    }

    public void setSeriesGroup(@Changeable(SERIESGROUP) String seriesGroup) {
        this.seriesGroup = seriesGroup;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(@Changeable(SEASON) String season) {
        this.season = season;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(@Changeable(UNITS) String units) {
        this.units = units;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(@Changeable(FREQUENCY) String frequency) {
        this.frequency = frequency;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(@Changeable(MINDATE) Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(@Changeable(MAXDATE) Date maxDate) {
        this.maxDate = maxDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
        result = prime * result + ((maxDate == null) ? 0 : maxDate.hashCode());
        result = prime * result + ((minDate == null) ? 0 : minDate.hashCode());
        result = prime * result + ((regionType == null) ? 0 : regionType.hashCode());
        result = prime * result + ((season == null) ? 0 : season.hashCode());
        result = prime * result + ((seriesGroup == null) ? 0 : seriesGroup.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((units == null) ? 0 : units.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SeriesGroup other = (SeriesGroup) obj;
        if (frequency == null) {
            if (other.frequency != null)
                return false;
        } else if (!frequency.equals(other.frequency))
            return false;
        if (maxDate == null) {
            if (other.maxDate != null)
                return false;
        } else if (!maxDate.equals(other.maxDate))
            return false;
        if (minDate == null) {
            if (other.minDate != null)
                return false;
        } else if (!minDate.equals(other.minDate))
            return false;
        if (regionType == null) {
            if (other.regionType != null)
                return false;
        } else if (!regionType.equals(other.regionType))
            return false;
        if (season == null) {
            if (other.season != null)
                return false;
        } else if (!season.equals(other.season))
            return false;
        if (seriesGroup == null) {
            if (other.seriesGroup != null)
                return false;
        } else if (!seriesGroup.equals(other.seriesGroup))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (units == null) {
            if (other.units != null)
                return false;
        } else if (!units.equals(other.units))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SeriesGroup [title=" + title + ", regionType=" + regionType + ", seriesGroup=" + seriesGroup
            + ", season=" + season + ", units=" + units + ", frequency=" + frequency + ", minDate=" + minDate
            + ", maxDate=" + maxDate + "]";
    }
}
