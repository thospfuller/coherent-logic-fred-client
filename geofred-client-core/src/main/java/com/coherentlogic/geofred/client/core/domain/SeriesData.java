package com.coherentlogic.geofred.client.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_data.html">GeoFRED API - Series Data</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesData extends SerializableBean<SeriesData> {

    private static final long serialVersionUID = -3243831219785407535L;

    public static final String
        TITLE = "title",
        REGION = "region",
        SEASONALITY = "seasonality",
        UNITS = "units",
        FREQUENCY = "frequency",
        DATA = "data",
        DATE = "date",
        OBSERVATION_LIST = "observationList";

    private String title;
    private String region;
    private String seasonality;
    private String units;
    private String frequency;

    private Date date;

    private List<Observation> observationList;

    public SeriesData () {
        this(new ArrayList<Observation> ());
    }

    public SeriesData (List<Observation> observationList) {
        this.observationList = observationList;
    }

    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(@Changeable(OBSERVATION_LIST) List<Observation> observationList) {
        this.observationList = observationList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@Changeable(TITLE) String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(@Changeable(REGION) String region) {
        this.region = region;
    }

    public String getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(@Changeable(SEASONALITY) String seasonality) {
        this.seasonality = seasonality;
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

    public Date getDate() {
        return date;
    }

    public void setDate(@Changeable(DATE) Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
        result = prime * result + ((observationList == null) ? 0 : observationList.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((seasonality == null) ? 0 : seasonality.hashCode());
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
        SeriesData other = (SeriesData) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (frequency == null) {
            if (other.frequency != null)
                return false;
        } else if (!frequency.equals(other.frequency))
            return false;
        if (observationList == null) {
            if (other.observationList != null)
                return false;
        } else if (!observationList.equals(other.observationList))
            return false;
        if (region == null) {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        if (seasonality == null) {
            if (other.seasonality != null)
                return false;
        } else if (!seasonality.equals(other.seasonality))
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
        return "SeriesData [title=" + title + ", region=" + region + ", seasonality=" + seasonality + ", units=" + units
            + ", frequency=" + frequency + ", date=" + date + ", observationList=" + observationList + "]";
    }
}
