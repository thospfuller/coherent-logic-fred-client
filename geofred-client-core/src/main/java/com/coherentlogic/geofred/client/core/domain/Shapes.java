package com.coherentlogic.geofred.client.core.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;

/**
 * 
 * For example:
 * 
 * https://api.stlouisfed.org/geofred/shapes/file?api_key=[TBD]&shape=censusdivision
 * 
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">GeoFRED API - Shape Files</a>
 * 
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class Shapes extends SerializableBean<Shapes> {

	private static final long serialVersionUID = 4029400163382584937L;

	public static final String BEAN_NAME = "shapes";

    private ShapeType shapeType;

    private List<Shape> shapeList;

    public Shapes() {
        this (new ArrayList<Shape> ());
    }

    public Shapes(List<Shape> shapeList) {
        super();
        this.shapeList = shapeList;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public void addShape(Shape... shapes) {
        shapeList.addAll(Arrays.asList(shapes));
    }

    @Override
    public String toString() {
        return "Shapes [shapeType=" + shapeType + ", shapeList=" + shapeList + "]";
    }
}
