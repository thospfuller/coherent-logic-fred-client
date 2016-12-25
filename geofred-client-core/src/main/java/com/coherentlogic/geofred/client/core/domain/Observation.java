package com.coherentlogic.geofred.client.core.domain;

import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_data.html">GeoFRED API - Series Data</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class Observation extends SerializableBean<Observation> {

    private static final long serialVersionUID = 5420522367192074023L;

    private static final String
        REGION = "region",
        CODE = "code",
        VALUE = "value",
        SERIESID = "seriesId";

    private String region;

    private String code;

    private String value;

    private String seriesId;

    public String getRegion() {
        return region;
    }

    public void setRegion(@Changeable(REGION) String region) {
        this.region = region;
    }

    public String getCode() {
        return code;
    }

    public void setCode(@Changeable(CODE) String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(@Changeable(VALUE) String value) {
        this.value = value;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(@Changeable(SERIESID) String seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((seriesId == null) ? 0 : seriesId.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Observation other = (Observation) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (region == null) {
            if (other.region != null)
                return false;
        } else if (!region.equals(other.region))
            return false;
        if (seriesId == null) {
            if (other.seriesId != null)
                return false;
        } else if (!seriesId.equals(other.seriesId))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Observation [region=" + region + ", code=" + code + ", value=" + value + ", seriesId=" + seriesId + "]";
    }
}

//<series_data 
//title="Per Capita Personal Income by State (Dollars)"
//region="state"
//seasonality="Not Seasonally Adjusted"
//units="Dollars"
//frequency="Annual"
//date="2012-01-01">
//<observation region="Alabama" code="01" value="35942" series_id="ALPCPI"/>
//<observation region="Alaska" code="02" value="49906" series_id="AKPCPI"/>
//<observation region="Arizona" code="04" value="36624" series_id="AZPCPI"/>
//<observation region="Arkansas" code="05" value="36423" series_id="ARPCPI"/>
//<observation region="California" code="06" value="47505" series_id="CAPCPI"/>
//<observation region="Colorado" code="08" value="46315" series_id="COPCPI"/>
//<observation region="Connecticut" code="09" value="60223" series_id="CTPCPI"/>