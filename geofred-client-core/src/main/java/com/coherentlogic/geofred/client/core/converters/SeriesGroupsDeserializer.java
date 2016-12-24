package com.coherentlogic.geofred.client.core.converters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.geofred.client.core.builders.QueryBuilder;
import com.coherentlogic.geofred.client.core.domain.SeriesGroup;
import com.coherentlogic.geofred.client.core.domain.SeriesGroups;
import com.coherentlogic.geofred.client.core.domain.Shape;
import com.coherentlogic.geofred.client.core.domain.ShapeType;
import com.coherentlogic.geofred.client.core.domain.Shapes;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SeriesGroupsDeserializer implements JsonDeserializer<SeriesGroups> {

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public SeriesGroups deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {

        SeriesGroups result = applicationContext.getBean(SeriesGroups.class);

        log.info("deserialize: method begins; element: " + element + ", typeOfT: " + typeOfT + ", context: " + context);

        JsonObject object = element.getAsJsonObject();

        JsonArray jsonArray = object.getAsJsonArray("series_group");

        if (jsonArray == null || jsonArray.isJsonNull()) {
            log.warn("No series_group array in the element " + element);
        } else {

            List<SeriesGroup> seriesGroupList = toSeriesGroupList (jsonArray);

            result.getSeriesGroupList().addAll(seriesGroupList);
        }

        log.info("deserialize: method ends; result: " + result);

        return result;
    }

    protected List<SeriesGroup> toSeriesGroupList (JsonArray jsonArray) {
        return toSeriesGroupList (jsonArray, new ArrayList<SeriesGroup> ());
    }

    protected List<SeriesGroup> toSeriesGroupList (JsonArray jsonArray, List<SeriesGroup> seriesGroupList) {

        jsonArray.forEach(
            jsonElement -> {

                log.info("toSeriesGroupList; jsonElement: " + jsonElement);

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                SeriesGroup seriesGroup = applicationContext.getBean(SeriesGroup.class);

                seriesGroup.setTitle(jsonObject.get(SeriesGroup.TITLE).getAsString());

                seriesGroupList.add(seriesGroup);
            }
        );

        return seriesGroupList;
    }
}
