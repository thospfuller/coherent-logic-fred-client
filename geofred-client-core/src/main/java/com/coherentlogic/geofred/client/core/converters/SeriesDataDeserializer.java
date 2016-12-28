package com.coherentlogic.geofred.client.core.converters;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.coherentlogic.geofred.client.core.domain.Observation;
import com.coherentlogic.geofred.client.core.domain.SeriesData;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Deserializer implementation for series data-related data.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesDataDeserializer implements JsonDeserializer<SeriesData> {

    private static final Logger log = LoggerFactory.getLogger(SeriesDataDeserializer.class);

    private static final String META = "meta";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public SeriesData deserialize(JsonElement element, Type type, JsonDeserializationContext context)
        throws JsonParseException {

        log.info("deserialize: method begins; element: " + element + ", type: " + type + ", context: " + context);

        SeriesData result = applicationContext.getBean(SeriesData.class);

        JsonObject object = element.getAsJsonObject();

        JsonObject metaObject = object.getAsJsonObject("meta");

        toSeriesData (metaObject, result);

        log.info("deserialize: method ends; result: " + result);

        return result;
    }

    protected SeriesData toSeriesData (JsonObject metaObject, SeriesData seriesData) {

        String title = metaObject.get(SeriesData.TITLE).toString();

        seriesData.setTitle(title);

        String region = metaObject.get(SeriesData.REGION).toString();

        seriesData.setRegion(region);

        String seasonality = metaObject.get(SeriesData.SEASONALITY).toString();

        seriesData.setSeasonality(seasonality);

        String units = metaObject.get(SeriesData.UNITS).toString();

        seriesData.setUnits(units);

        String frequency = metaObject.get(SeriesData.FREQUENCY).toString();

        seriesData.setFrequency(frequency);

        JsonObject dataObject = metaObject.getAsJsonObject(SeriesData.DATA);

        dataObject.entrySet().forEach(
            consumer -> {

                String date = consumer.getKey();

                JsonElement value = consumer.getValue();

                JsonArray observations = value.getAsJsonArray();

                observations.forEach(
                    observationElement -> {

                        Observation observation = toObservation (date, observationElement);

                        seriesData.getObservationList().add(observation);
                    }
                );
            }
        );

        return seriesData;
    }

    protected Observation toObservation (String date, JsonElement observationElement) {

        JsonObject observationObject = observationElement.getAsJsonObject();

        return toObservation (date, observationObject);
    }

    protected Observation toObservation (String date, JsonObject observationObject) {

        Observation result = applicationContext.getBean(Observation.class);

        result.setDate(date);

        String region = observationObject.get(Observation.REGION).getAsString();

        result.setRegion(region);

        String code = observationObject.get(Observation.CODE).getAsString();

        result.setCode(code);

        String value = observationObject.get(Observation.VALUE).getAsString();

        result.setValue(value);

        String seriesId = observationObject.get(Observation.SERIES_ID).getAsString();

        result.setSeriesId(seriesId);

        return result;
    }
}
/*
 * https://api.stlouisfed.org/geofred/series/data?file_type=json&api_key=[TBD]&series_id=WIPCPI&start_date=2012-01-01
 * {"meta":
 * {"title":"Per Capita Personal Income by State (Dollars)","region":"state","seasonality":"Not Seasonally Adjusted","units":"Dollars","frequency":"Annual",
 * "data":{
 *     "2012":[
 *         {"region":"Alabama","code":"01","value":"36036","series_id":"ALPCPI"},
 *         {"region":"Alaska","code":"02","value":"52269","series_id":"AKPCPI"},
 *         {"region":"Arizona","code":"04","value":"36788","series_id":"AZPCPI"},{"region":"Arkansas","code":"05","value":"36291","series_id":"ARPCPI"},{"region":"California","code":"06","value":"47614","series_id":"CAPCPI"},{"region":"Colorado","code":"08","value":"46402","series_id":"COPCPI"},{"region":"Connecticut","code":"09","value":"62738","series_id":"CTPCPI"},{"region":"Delaware","code":"10","value":"44747","series_id":"DEPCPI"},{"region":"District of Columbia","code":"11","value":"68973","series_id":"DCPCPI"},{"region":"Florida","code":"12","value":"41249","series_id":"FLPCPI"},{"region":"Georgia","code":"13","value":"37254","series_id":"GAPCPI"},{"region":"Hawaii","code":"15","value":"44504","series_id":"HIPCPI"},{"region":"Idaho","code":"16","value":"34846","series_id":"IDPCPI"},{"region":"Illinois","code":"17","value":"46067","series_id":"ILPCPI"},{"region":"Indiana","code":"18","value":"37987","series_id":"INPCPI"},{"region":"Iowa","code":"19","value":"43458","series_id":"IAPCPI"},{"region":"Kansas","code":"20","value":"43725","series_id":"KSPCPI"},{"region":"Kentucky","code":"21","value":"35814","series_id":"KYPCPI"},{"region":"Louisiana","code":"22","value":"40527","series_id":"LAPCPI"},{"region":"Maine","code":"23","value":"39589","series_id":"MEPCPI"},{"region":"Maryland","code":"24","value":"53078","series_id":"MDPCPI"},{"region":"Massa
 */
