package com.coherentlogic.geofred.client.core.converters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.geofred.client.core.domain.Shape;
import com.coherentlogic.geofred.client.core.domain.ShapeType;
import com.coherentlogic.geofred.client.core.domain.Shapes;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializer implementation for shape-related data.
 *
 * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">GeoFRED API - Shape Files</a>
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ShapesDeserializer implements JsonDeserializer<Shapes> {

    private static final Logger log = LoggerFactory.getLogger(ShapesDeserializer.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Shapes deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException {

        log.info("deserialize: method begins; element: " + element + ", type: " + type + ", context: " + context);

        Shapes result = applicationContext.getBean(Shapes.class);

        JsonObject object = element.getAsJsonObject();

        object.entrySet().forEach(
            entry -> {

                String key = entry.getKey();

                JsonElement valueElement = entry.getValue();

                JsonArray valueArray = valueElement.getAsJsonArray();

                result.setShapeType(ShapeType.valueOf(key));

                List<Shape> shapeList = toShapeList (valueArray);

                result.getShapeList().addAll(shapeList);
            }
        );

        log.info("deserialize: method ends; result: " + result);

        return result;
    }

    protected List<Shape> toShapeList (JsonArray jsonArray) {
        return toShapeList (jsonArray, new ArrayList<Shape> ());
    }

    protected List<Shape> toShapeList (JsonArray jsonArray, List<Shape> shapeList) {

        jsonArray.forEach(
            jsonElement -> {

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                Shape shape = applicationContext.getBean(Shape.class);

                String name = jsonObject.get(Shape.NAME).getAsString();

                Integer code = jsonObject.get(Shape.CODE).getAsInt();

                String geometry = jsonObject.get(Shape.GEOMETRY).getAsString();

                String centroid = jsonObject.get(Shape.CENTROID).getAsString();

                shape.setName(name);
                shape.setCode(code);
                shape.setGeometry(geometry);
                shape.setCentroid(centroid);

                shapeList.add(shape);
            }
        );

        return shapeList;
    }
}
