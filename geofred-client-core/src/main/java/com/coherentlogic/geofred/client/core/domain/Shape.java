package com.coherentlogic.geofred.client.core.domain;

import com.coherentlogic.coherent.data.model.core.annotations.Changeable;
import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 *
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">GeoFRED API - Shape Files</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class Shape extends SerializableBean<Shape> {

    private static final long serialVersionUID = -3610816663831532438L;

    public static final String BEAN_NAME = "shape";

    public static final String NAME = "name", CODE = "code", CENTROID = "centroid", GEOMETRY = "geometry";

    private String name;

    private Integer code;

    private String centroid;

    private String geometry;

    public String getName() {
        return name;
    }

    public void setName(@Changeable(NAME) String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(@Changeable(CODE) Integer code) {
        this.code = code;
    }

    public String getCentroid() {
        return centroid;
    }

    public void setCentroid(@Changeable(CENTROID) String centroid) {
        this.centroid = centroid;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(@Changeable(GEOMETRY) String geometry) {
        this.geometry = geometry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((centroid == null) ? 0 : centroid.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Shape other = (Shape) obj;
        if (centroid == null) {
            if (other.centroid != null)
                return false;
        } else if (!centroid.equals(other.centroid))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (geometry == null) {
            if (other.geometry != null)
                return false;
        } else if (!geometry.equals(other.geometry))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Shape [name=" + name + ", code=" + code + ", centroid=" + centroid + ", geometry=" + geometry + "]";
    }
}

//Available Shape Types:
//    bea (Bureau of Economic Anaylis Region)
//    msa (Metropolitan Statistical Area)
//    frb (Federal Reserve Bank Districts)
//    necta (New England City and Town Area)
//    state
//    country
//    county (USA Counties)
//    censusregion (US Census Regions)
//    censusdivision (US Census Divisons)
