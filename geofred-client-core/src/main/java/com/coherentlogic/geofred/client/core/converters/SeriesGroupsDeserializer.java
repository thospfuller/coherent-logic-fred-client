package com.coherentlogic.geofred.client.core.converters;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.coherent.data.adapter.core.exceptions.ConversionFailedException;
import com.coherentlogic.geofred.client.core.domain.SeriesGroup;
import com.coherentlogic.geofred.client.core.domain.SeriesGroups;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializer implementation for series group-related data.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesGroupsDeserializer implements JsonDeserializer<SeriesGroups> {

    private static final Logger log = LoggerFactory.getLogger(SeriesGroupsDeserializer.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public SeriesGroups deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException {

        log.info("deserialize: method begins; element: " + element + ", type: " + type + ", context: " + context);

        SeriesGroups result = applicationContext.getBean(SeriesGroups.class);

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

    /**
     * {"series_group":[{"title":"All Employees: Total Private","region_type":"state","series_group":"1223",
     *  "season":"NSA","units":"Thousands of Persons","frequency":"a","min_date":"1990-01-01","max_date":"2015-01-01"}]}
     */
    protected List<SeriesGroup> toSeriesGroupList (JsonArray jsonArray, List<SeriesGroup> seriesGroupList) {

        jsonArray.forEach(
            jsonElement -> {

                log.info("toSeriesGroupList; jsonElement: " + jsonElement);

                JsonObject jsonObject = jsonElement.getAsJsonObject();

                SeriesGroup seriesGroup = applicationContext.getBean(SeriesGroup.class);

                String title = jsonObject.get(SeriesGroup.TITLE).getAsString();

                seriesGroup.setTitle(title);

                String regionType = jsonObject.get(SeriesGroup.REGION_TYPE).getAsString();

                seriesGroup.setRegionType(regionType);

                String frequency = jsonObject.get(SeriesGroup.FREQUENCY).getAsString();

                seriesGroup.setFrequency(frequency);

                String seriesGroupText = jsonObject.get(SeriesGroup.SERIES_GROUP).getAsString();

                seriesGroup.setSeriesGroup(seriesGroupText);

                String season = jsonObject.get(SeriesGroup.SEASON).getAsString();

                seriesGroup.setSeason(season);

                String units = jsonObject.get(SeriesGroup.UNITS).getAsString();

                seriesGroup.setUnits(units);

                JsonElement minDateElement = jsonObject.get(SeriesGroup.MIN_DATE);

                Date minDate = toDate (minDateElement);

                seriesGroup.setMinDate(minDate);

                JsonElement maxDateElement = jsonObject.get(SeriesGroup.MAX_DATE);

                Date maxDate = toDate (maxDateElement);

                seriesGroup.setMaxDate(maxDate);

                seriesGroupList.add(seriesGroup);
            }
        );

        return seriesGroupList;
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-mm-dd");

    protected Date toDate (JsonElement dateElement) {
        return toDate (dateElement.getAsString());
    }

    protected Date toDate (String dateText) {

        Date result = null;

        try {
            result = dateFormat.parse(dateText);
        } catch (ParseException e) {
            throw new ConversionFailedException("Unable to parse the date: " + dateText, e);
        }

        return result;
    }
}
