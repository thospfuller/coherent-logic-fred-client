package com.coherentlogic.geofred.client.core.builders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.core.builders.rest.AbstractRESTQueryBuilder;
import com.coherentlogic.coherent.data.adapter.core.cache.CacheServiceProviderSpecification;
import com.coherentlogic.coherent.data.adapter.core.util.WelcomeMessage;
//import com.coherentlogic.fred.client.core.domain.SortOrder;
//import com.coherentlogic.fred.client.core.domain.Sources;
//import com.coherentlogic.fred.client.core.domain.TagGroupId;
//import com.coherentlogic.fred.client.core.domain.Tags;
//import com.coherentlogic.fred.client.core.domain.Unit;
//import com.coherentlogic.fred.client.core.domain.VintageDates;
//import com.coherentlogic.fred.client.core.exceptions.DateOutOfBoundsException;
//import com.coherentlogic.fred.client.core.exceptions.InvalidDateFormatException;
//import com.coherentlogic.fred.client.core.exceptions.InvalidParameterValue;
//import com.coherentlogic.fred.client.core.exceptions.LimitOutOfBoundsException;
//import com.coherentlogic.fred.client.core.exceptions.OffsetOutOfBoundsException;
import com.coherentlogic.fred.client.core.services.GoogleAnalyticsMeasurementService;
import com.coherentlogic.fred.client.domain.FileType;
import com.coherentlogic.geofred.client.core.domain.SeriesData;
import com.coherentlogic.geofred.client.core.domain.SeriesGroups;
import com.coherentlogic.geofred.client.core.domain.ShapeType;
//import com.coherentlogic.fred.client.core.domain.AggregationMethod;
//import com.coherentlogic.fred.client.core.domain.Categories;
//import com.coherentlogic.fred.client.core.domain.FileType;
//import com.coherentlogic.fred.client.core.domain.FilterValue;
//import com.coherentlogic.fred.client.core.domain.FilterVariable;
//import com.coherentlogic.fred.client.core.domain.Frequency;
//import com.coherentlogic.fred.client.core.domain.Observations;
//import com.coherentlogic.fred.client.core.domain.OrderBy;
//import com.coherentlogic.fred.client.core.domain.OutputType;
//import com.coherentlogic.fred.client.core.domain.ReleaseDates;
//import com.coherentlogic.fred.client.core.domain.Releases;
//import com.coherentlogic.fred.client.core.domain.SearchType;
//import com.coherentlogic.fred.client.core.domain.Seriess;
import com.coherentlogic.geofred.client.core.domain.Shapes;

/**
 * Class that allows the developer to construct and execute a query to the Federal Reserve Bank of St. Louis' FRED web
 * services.
 * <p>
 * Note that this class is <b>not</b> thread-safe and cannot be used as a member-level property -- if this is required,
 * use the {@link com.coherentlogic.geofred.client.core.factories.QueryBuilderFactory QueryBuilderFactory} class.
 * <p>
 * In order to facilitate method-chaining each "with" method returns a reference to this object.
 * <p>
 * For examples, refer to the {@link QueryBuilderTest} class in the fred-client-core-it module.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class QueryBuilder extends AbstractRESTQueryBuilder<String> {

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    static final String[] WELCOME_MESSAGE = {
        "*************************************************************************************************************",
        "***                                                                                                       ***",
        "***                                     Welcome to the GeoFRED Client                                     ***",
        "***                                                                                                       ***",
        "***                                        Version  2.0.0-RELEASE                                         ***",
        "***                                                                                                       ***",
        "***                              Please take a moment to follow us on Twitter:                            ***",
        "***                                                                                                       ***",
        "***                                    www.twitter.com/CoherentMktData                                    ***",
        "***                                                                                                       ***",
        "***                                          or on LinkedIn:                                              ***",
        "***                                                                                                       ***",
        "***                            www.linkedin.com/company/coherent-logic-limited                            ***",
        "***                                                                                                       ***",
        "***                            The project and issue tracker can be found here:                           ***",
        "***                                                                                                       ***",
        "***                     https://bitbucket.org/CoherentLogic/coherent-logic-fred-client                    ***",
        "***                                                                                                       ***",
        "*** ----------------------------------------------------------------------------------------------------- ***",
        "***                                                                                                       ***",
        "*** BE ADVISED:                                                                                           ***",
        "***                                                                                                       ***",
        "*** This framework uses the Google Analytics Measurement API (GAM) to track framework usage  information. ***",
        "*** As this software is open-source, you are welcomed to review our use of GAM -- please  see  the  class ***",
        "*** named  com.coherentlogic.fred.client.core.services.GoogleAnalyticsMeasurementService  and  feel  free ***",
        "*** to send us an email if you have further questions.                                                    ***",
        "***                                                                                                       ***",
        "*** We do NOT recommend disabling this feature however we offer the option below, just add the following  ***",
        "*** VM parameter and tracking will be disabled:                                                           ***",
        "***                                                                                                       ***",
        "*** -DGOOGLE_ANALYTICS_TRACKING=false                                                                     ***",
        "***                                                                                                       ***",
        "*** ----------------------------------------------------------------------------------------------------- ***",
        "***                                                                                                       ***",
        "*** We offer support and consulting services to businesses that  utilize  this  framework  or  that  have ***",
        "*** custom financial data acquisition projects -- inquiries can be directed to:                           ***",
        "***                                                                                                       ***",
        "*** [M] sales@coherentlogic.com                                                                           ***",
        "*** [T] +1.571.306.3403 (GMT-5)                                                                           ***",
        "***                                                                                                       ***",
        "*************************************************************************************************************"
    };

    /**
     * Todo: Move this message so that it appears in the AbstractQueryBuilder.
     */
    static {

        GoogleAnalyticsMeasurementService googleAnalyticsMeasurementService = new GoogleAnalyticsMeasurementService (
            "GeoFRED Client");

        if (googleAnalyticsMeasurementService.shouldTrack()) {
            try {
                googleAnalyticsMeasurementService.fireGAFrameworkUsageEvent ();
            } catch (Throwable thrown) {
                log.warn("fireGAFrameworkUsageEvent: method call failed. This exception can be ignored, and the "
                    + "framework will function without issue.", thrown);
            }
        }

        WelcomeMessage welcomeMessage = new WelcomeMessage();

        for (String next : WELCOME_MESSAGE) {
            welcomeMessage.addText(next);
        }

        welcomeMessage.display();
    }

    public static final String
        API_KEY = "api_key",
        CATEGORY_ID = "category_id",
        REALTIME_START = "realtime_start",
        REALTIME_END = "realtime_end",
        LIMIT = "limit",
        OFFSET = "offset",
        ORDER_BY = "order_by",
        SORT_ORDER = "sort_order",
        FILTER_VARIABLE = "filter_variable",
        FILTER_VALUE = "filter_value",
        INCLUDE_RELEASE_DATES_WITH_NO_DATA = "include_release_dates_with_no_data",
        RELEASE_ID = "release_id",
        OBSERVATION_START = "observation_start",
        OBSERVATION_END = "observation_end",
        UNITS = "units",
        FREQUENCY = "frequency",
        AGGREGATION_METHOD = "aggregation_method",
        OUTPUT_TYPE = "output_type",
        FILE_TYPE = "file_type",
        VINTAGE_DATES = "vintagedates",
        VINTAGE_DATES_PARAM = "vintage_dates",
        SERIES_ID = "series_id",
        SOURCE_ID = "source_id",
        SEARCH_TEXT = "search_text",
        SEARCH_TYPE = "search_type",
        SHAPES = "shapes",
        SHAPE = "shape",
        FILE = "file",
        SEMICOLON = ";",
        GEOFRED_API_ENTRY_POINT = "https://api.stlouisfed.org/geofred",
        SERIES = "series",
        GROUP = "group",
        DATA = "data",
        DATE = "date",
        START_DATE = "start_date";

    /**
     * A constructor that takes a RestTemplate and a uri that points to the web service endpoint.
     *
     * @param restTemplate Used to make the call to the web service and convert the XML into an instance of a domain
     *  class.
     *
     * @param uri The uri to the web service endpoint.
     *
     * @see com.coherentlogic.coherent.data.model.core.builders.AbstractQueryBuilder
     */
    public QueryBuilder (RestTemplate restTemplate, String uri) {
        super (restTemplate, uri);
    }

    public QueryBuilder (RestTemplate restTemplate) {
        super (restTemplate, GEOFRED_API_ENTRY_POINT);
    }

    /**
     * A constructor that takes a RestTemplate, String (uri), and a Map which is used to cache results returned from the
     * call to the FRED web service.
     *
     * @param restTemplate Used to make the call to the web service and convert the XML into an instance of a domain
     *  class.
     *
     * @param uri The uri to the web service endpoint.
     *
     * @param cache A cache implementation such as the JBoss <a
     *  href="http://www.jboss.org/infinispan/">Infinispan</a> cache.
     *
     * @see com.coherentlogic.coherent.data.model.core.builders.AbstractQueryBuilder
     */
    public QueryBuilder(
        RestTemplate restTemplate,
        String uri,
        CacheServiceProviderSpecification<String, Object> cache
    ) {
        super(restTemplate, uri, cache);
    }

    public QueryBuilder(RestTemplate restTemplate, CacheServiceProviderSpecification<String, Object> cache) {
        super(restTemplate, GEOFRED_API_ENTRY_POINT, cache);
    }

    /**
     * A constructor that takes a RestTemplate and a UriBuilder.
     *
     * @param restTemplate Used to make the call to the web service and convert the XML into an instance of a domain
     *  class.
     *
     * @param uriBuilder Used to construct the URI.
     *
     * @see com.coherentlogic.coherent.data.model.core.builders.AbstractQueryBuilder
     */
    public QueryBuilder (RestTemplate restTemplate, UriBuilder uriBuilder) {
        super (restTemplate, uriBuilder);
    }

    /**
     * A constructor that takes a RestTemplate, UriBuilder, and a Map which is used to cache results returned from the
     * call to the FRED web service.
     *
     * @param restTemplate Used to make the call to the web service and convert the XML into an instance of a domain
     *  class.
     *
     * @param uriBuilder Used to construct the URI.
     *
     * @param cache A cache implementation such as the JBoss <a
     *  href="http://www.jboss.org/infinispan/">Infinispan</a> cache.
     *
     * @see com.coherentlogic.coherent.data.model.core.builders.AbstractQueryBuilder
     */
    public QueryBuilder(
        RestTemplate restTemplate,
        UriBuilder uriBuilder,
        CacheServiceProviderSpecification<String, Object> cache
    ) {
        super(restTemplate, uriBuilder, cache);
    }

    /**
     * Setter method for the API key parameter. Note the API key is required by every FRED web service call.
     *
     * Register for an API key <a href="https://api.stlouisfed.org/api_key.html"> here</a>.
     *
     * @param apiKey For example, "abcdefghijklmnopqrstuvwxyz123456".
     */
    public QueryBuilder withApiKey (String apiKey) {

        addParameter(API_KEY, apiKey);

        return this;
    }

    /**
     * The FRED series id you want to request GeoFRED meta information for. Not all series that are in FRED have
     * geographical data.
     *
     * @param seriesId For example, "GNPCA".
     */
    public QueryBuilder withSeriesId (String seriesId) {

        addParameter(SERIES_ID, seriesId);

        return this;
    }

    /**
     * Adds the date name/value pair. Note that the format of the date string is not checked for correctness.
     *
     * @param date The date value.
     */
    public QueryBuilder withDate (String date) {

        addParameter(DATE, date);

        return this;
    }

    /**
     * Consider a date of 1992, Calendar.JULY, 20 (day) the call to {#link SimpleDateFormat#format} would yield
     * "1992-07-20".
     */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");

    /**
     * Adds the date name/value pair formatting the date value using the {@link #dateFormat}.
     *
     * @param date The date value.
     */
    public QueryBuilder withDate (Date date) {

        addParameter(DATE, dateFormat, date);

        return this;
    }

    /**
     * Adds the startDate name/value pair. Note that the format of the startDate string is not checked for correctness.
     *
     * @param startDate The startDate value.
     */
    public QueryBuilder withStartDate (String startDate) {

        addParameter(START_DATE, startDate);

        return this;
    }

    /**
     * Adds the startDate name/value pair formatting the date value using the {@link #dateFormat}.
     *
     * @param startDate The startDate value.
     */
    public QueryBuilder withStartDate (Date startDate) {

        addParameter(START_DATE, dateFormat, startDate);

        return this;
    }

//    api_key
//    file_type
//    series_group
//    region_type
//    date
//    start_date
//    frequency
//    units
//    season
//    transformation
//    aggregation_method


//    /**
//     * Setter method for the release id parameter.
//     *
//     * @param releaseId For example, 53L.
//     */
//    public QueryBuilder withReleaseId (long releaseId) {
//
//        addParameter(RELEASE_ID, Long.toString(releaseId));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the category id parameter.
//     */
//    public QueryBuilder withCategoryId (long categoryId) {
//
//        addParameter(CATEGORY_ID, Long.toString(categoryId));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the source id parameter.
//     */
//    public QueryBuilder withSourceId (long sourceId) {
//
//        addParameter(SOURCE_ID, Long.toString(sourceId));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the real-time start date parameter.
//     */
//    public QueryBuilder withRealtimeStart (Date realtimeStart) {
//
//        boolean between = isBetween (realtimeStart);
//
//        if (!between)
//            throw new DateOutOfBoundsException(realtimeStart);
//
//        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);
//
//        String realtimeStartText = dateFormat.format(realtimeStart);
//
//        addParameter(REALTIME_START, realtimeStartText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the real-time start date parameter.
//     *
//     * @throws InvalidDateFormatException When the date does not conform to the expected format
//     *  (ie. yyyy-MM-dd / 2012-06-21).
//     */
//    public QueryBuilder withRealtimeStart (String realtimeStart) {
//
//        assertDateFormatIsValid ("realtimeStart", realtimeStart);
//
//        addParameter(REALTIME_START, realtimeStart);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the real-time end date parameter.
//     */
//    public QueryBuilder withRealtimeEnd (Date realtimeEnd) {
//
//        boolean between = isBetween (realtimeEnd);
//
//        if (!between)
//            throw new DateOutOfBoundsException(realtimeEnd);
//
//        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);
//
//        String realtimeEndText = dateFormat.format(realtimeEnd);
//
//        addParameter(REALTIME_END, realtimeEndText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the real-time end date parameter.
//     *
//     * @throws InvalidDateFormatException When the date does not conform to the expected format
//     *  (ie. yyyy-MM-dd / 2012-06-21).
//     */
//    public QueryBuilder withRealtimeEnd (String realtimeEnd) {
//
//        assertDateFormatIsValid ("realtimeEnd", realtimeEnd);
//
//        addParameter(REALTIME_END, realtimeEnd);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the limit parameter.
//     *
//     * @param limit A value between 1 and 100000 (inclusive).
//     */
//    public QueryBuilder withLimit (long limit) {
//
//        if (!(1 <= limit && limit <= 100000))
//            throw new LimitOutOfBoundsException(limit);
//
//        addParameter(LIMIT, Long.toString(limit));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the offset parameter.
//     *
//     * @param offset A non-negative integer.
//     *
//     * @throws OffsetOutOfBoundsException if the value of the offset is less than zero.
//     */
//    public QueryBuilder withOffset (int offset) {
//
//        if (offset < 0)
//            throw new OffsetOutOfBoundsException (offset);
//
//        addParameter(OFFSET, Integer.toString(offset));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the order-by parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.OrderBy
//     */
//    public QueryBuilder withOrderBy (OrderBy orderBy) {
//
//        addParameter(ORDER_BY, orderBy.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the sort order parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.SortOrder
//     */
//    public QueryBuilder withSortOrder (SortOrder sortOrder) {
//
//        addParameter(SORT_ORDER, sortOrder.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the filter variable parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.FilterVariable
//     */
//    public QueryBuilder withFilterVariable (FilterVariable filterVariable) {
//
//        addParameter(FILTER_VARIABLE, filterVariable.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the filter value parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.FilterValue
//     */
//    public QueryBuilder withFilterValue (FilterValue filterValue) {
//
//        addParameter(FILTER_VALUE, filterValue.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the include-release-dates-with-no-data flag parameter.
//     */
//    public QueryBuilder withIncludeReleaseDatesWithNoData (boolean value) {
//
//        addParameter(INCLUDE_RELEASE_DATES_WITH_NO_DATA, Boolean.toString(value));
//
//        return this;
//    }
//
//    /**
//     * Setter method for the observation start date parameter.
//     */
//    public QueryBuilder withObservationStart (Date observationStart) {
//
//        boolean between = isBetween (observationStart);
//
//        if (!between)
//            throw new DateOutOfBoundsException(observationStart);
//
//        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);
//
//        String observationStartText = dateFormat.format(observationStart);
//
//        addParameter(OBSERVATION_START, observationStartText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the observation start date parameter.
//     *
//     * @throws InvalidDateFormatException When the date does not conform to the expected format
//     *  (ie. yyyy-MM-dd / 2012-06-21).
//     */
//    public QueryBuilder withObservationStart (String observationStart) {
//
//        assertDateFormatIsValid ("observationStart", observationStart);
//
//        addParameter(OBSERVATION_START, observationStart);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the observation end date parameter.
//     *
//     * @param observationEnd
//     */
//    public QueryBuilder withObservationEnd (Date observationEnd) {
//
//        boolean between = isBetween (observationEnd);
//
//        if (!between)
//            throw new DateOutOfBoundsException(observationEnd);
//
//        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);
//
//        String observationEndText = dateFormat.format(observationEnd);
//
//        addParameter(OBSERVATION_END, observationEndText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the observation end date parameter parameter.
//     *
//     * @throws InvalidDateFormatException When the date does not conform to the expected format
//     *  (ie. yyyy-MM-dd / 2012-06-21).
//     */
//    public QueryBuilder withObservationEnd (String observationEnd) {
//
//        assertDateFormatIsValid ("observationEnd", observationEnd);
//
//        addParameter(OBSERVATION_END, observationEnd);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the units parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.Unit
//     */
//    public QueryBuilder withUnits (Unit unit) {
//
//        addParameter(UNITS, unit.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the frequency parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.Frequency
//     */
//    public QueryBuilder withFrequency (Frequency frequency) {
//
//        addParameter(FREQUENCY, frequency.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the aggregation method parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.AggregationMethod
//     */
//    public QueryBuilder withAggregationMethod (AggregationMethod aggregationMethod) {
//
//        addParameter(AGGREGATION_METHOD, aggregationMethod.toString());
//
//        return this;
//    }
//
//    /**
//     * Setter method for the output type parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.OutputType
//     */
//    public QueryBuilder withOutputType (OutputType outputType) {
//
//        addParameter(OUTPUT_TYPE, outputType.toString());
//
//        return this;
//    }

    /**
     * Setter method for the file type parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.FileType
     */
    public QueryBuilder withFileType (String fileType) {

        addParameter(FILE_TYPE, fileType);

        return this;
    }

    /**
     * Setter method for the file type parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.FileType
     */
    public QueryBuilder withFileType (FileType fileType) {
        return withFileType (fileType.toString());
    }

    public QueryBuilder withFileTypeAsXML() {
        return withFileType(FileType.xml);
    }

    public QueryBuilder withFileTypeAsJSON() {
        return withFileType(FileType.json);
    }

//    /**
//     * Setter method for the vinatage dates parameter, which can be a single date, or a list of dates separated by a
//     * comma.
//     *
//     * Note that the format of the dates is not checked client-side -- if there is an invalid date, the server will
//     * reject the call.
//     */
//    public QueryBuilder withVintageDates (String vintageDates) {
//
//        addParameter(VINTAGE_DATES_PARAM, vintageDates);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the array of vintage dates parameter.
//     *
//     * @throws InvalidDateFormatException If any of the dates are not of the format yyyy-MM-dd.
//     */
//    public QueryBuilder withVintageDates (String... vintageDates) {
//
//        String value = convertDates ("setVintageDates", ",", vintageDates);
//
//        addParameter(VINTAGE_DATES_PARAM, value);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the search text  parameter which consists of the words to match against economic data series.
//     */
//    public QueryBuilder withSearchText (String searchText) {
//
//        addParameter(SEARCH_TEXT, searchText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the series search text  parameter which consists of the words to match against economic data
//     * series -- for example "monetary service index".
//     */
//    public QueryBuilder withSeriesSearchText (String seriesSearchText) {
//
//        addParameter(SERIES_SEARCH_TEXT, seriesSearchText);
//
//        return this;
//    }
//
//    /**
//     * Setter method for the search type parameter.
//     *
//     * @see com.coherentlogic.fred.client.core.domain.SearchType
//     */
//    public QueryBuilder withSearchType (SearchType searchType) {
//
//        addParameter(SEARCH_TYPE, searchType.toString());
//
//        return this;
//    }
//
//    /**
//     * Method takes a single string, such as "slovenia;food;oecd", and set this as the tagName; this value filters
//     * results to match either tag "slovenia", "food", or "oecd".
//     *
//     * @see <a href="https://api.stlouisfed.org/docs/fred/series_search_related_tags.html">Series search related tags</a>
//     */
//    public QueryBuilder withTagNames (String tagNames) {
//
//        addParameter(TAG_NAMES, tagNames);
//
//        return this;
//    }
//
//    /**
//     * An array of tags to filter results by -- optional, no filtering by tags by default.
//     *
//     * For example: 'm1;m2' -- this value filters results to match either tag 'm1' or tag 'm2'.
//     *
//     * This method takes an array of strings, such as "slovenia", "food", "oecd" and creates a single aggregated string
//     * with each value separated by a semicolon (ie. "slovenia;food;oecd").
//     *
//     * @see <a href="https://api.stlouisfed.org/docs/fred/series_search_related_tags.html">Series search related tags</a>
//     */
//    public QueryBuilder withTagNames (String... tagNames) {
//
//        String result = combine (SEMICOLON, tagNames);
//
//        return withTagNames (result);
//    }
//
//    /**
//     * A tag group id to filter tags by type.
//     *
//     * String, optional, no filtering by tag group by default.
//     * One of the following: 'freq', 'gen', 'geo', 'geot', 'rls', 'seas', 'src'.
//     *
//     * freq = Frequency
//     * gen = General or Concept
//     * geo = Geography
//     * geot = Geography Type
//     * rls = Release
//     * seas = Seasonal Adjustment
//     * src = Source
//     */
//    public QueryBuilder withTagGroupId (TagGroupId tagGroupId) {
//
//        addParameter (TAG_GROUP_ID, tagGroupId.toString());
//
//        return this;
//    }
//
//    /**
//     * The words to find matching tags with -- optional, no filtering by search words by default.
//     */
//    public QueryBuilder withTagSearchText (String tagSearchText) {
//
//        addParameter (TAG_SEARCH_TEXT, tagSearchText);
//
//        return this;
//    }

    /**
     * Extends the path with shapes/file/ -- ie.
     *
     * https://api.stlouisfed.org/geofred/shapes/file?shape=bea&api_key=[TBD]
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder shapes () {

        extendPathWith(SHAPES);
        extendPathWith(FILE);

        return this;
    }

    /**
     * Extends the path with series/group/; note that the {@link #withFileTypeAsJSON()} method is also.
     *
     * Note that the {@link #withFileTypeAsJSON()} method is called from within this method.
     *
     * https://api.stlouisfed.org/geofred/series/group?series_id=SMU56000000500000001a&api_key=[TBD]
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_group.html">
     *  GeoFRED API - Series Group Info</a>
     */
    public QueryBuilder seriesGroup () {

        extendPathWith(SERIES);
        extendPathWith(GROUP);

        return withFileTypeAsJSON();
    }

    /**
     * Extends the path with series/data/; note that the {@link #withFileTypeAsJSON()} method is also.
     *
     * https://api.stlouisfed.org/geofred/series/group?series_id=SMU56000000500000001a&api_key=[TBD]
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/series_data.html">GeoFRED API - Series Data</a>
     */
    public QueryBuilder seriesData () {

        extendPathWith(SERIES);
        extendPathWith(DATA);

        return withFileTypeAsJSON();
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeType (String shapeType) {

        addParameter(SHAPE, shapeType);

        return this;
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeType (ShapeType shapeType) {
        return withShapeType (shapeType.toString());
    }

    /**
     * Bureau of Economic Analysis Region
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsBEA () {
        return withShapeType (ShapeType.bea);
    }

    /**
     * Metropolitan Statistical Area
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsMSA () {
        return withShapeType (ShapeType.msa);
    }

    /**
     * Federal Reserve Bank Districts
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsFRB () {
        return withShapeType (ShapeType.frb);
    }

    /**
     * New England City and Town Area
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsNECTA () {
        return withShapeType (ShapeType.necta);
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsState () {
        return withShapeType (ShapeType.state);
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsCountry () {
        return withShapeType (ShapeType.country);
    }

    /**
     * USA Counties
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsCounty () {
        return withShapeType (ShapeType.county);
    }

    /**
     * US Census Regions
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsCensusRegion () {
        return withShapeType (ShapeType.censusregion);
    }

    /**
     * US Census Divisons
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder withShapeTypeAsCensusDivision () {
        return withShapeType (ShapeType.censusdivision);
    }

//    static String combine (String seperator, String... values) {
//
//        assertNotNullOrEmpty ("values", values);
//
//        StringBuilder result = new StringBuilder ();
//
//        int ctr = values.length;
//
//        for (String next : values) {
//            result.append(next);
//
//            if (1 < ctr--) {
//                result.append(seperator);
//            }
//        }
//        return result.toString();
//    }
//
//    /**
//     * Todo: Move this method to the parent package.
//     */
//    static void assertNotNullOrEmpty (String variableName, Object[] values) {
//        if (values == null || values.length == 0)
//            throw new InvalidParameterValue ("The variable named '" +
//                variableName + "' is either null or empty (" +
//                ToStringBuilder.reflectionToString(values) + ").");
//    }
//
//    /**
//     * Method checks that the actual date is between the {@link #MIN_DATE_CALENDAR} and {@link #MAX_DATE_CALENDAR}
//     * dates.
//     *
//     * @param actual The actual date.
//     *
//     * @return True if the actual date is between the {@link #MIN_DATE_CALENDAR} and {@link #MAX_DATE_CALENDAR} dates.
//     */
//    private boolean isBetween (Date actual) {
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(actual);
//
//        int minValue = calendar.compareTo(MIN_DATE_CALENDAR);
//        int maxValue = calendar.compareTo(MAX_DATE_CALENDAR);
//
//        boolean result = (0 <= minValue) && (maxValue <= 0);
//
//        return result;
//    }
//
//    /**
//     * Method converts the <i>dates</i> into a single String, with each date separated by a comma.
//     *
//     * @param method The name of the method calling this method; this is used when an exception is thrown because one of
//     * the dates is in an invalid format.
//     *
//     * @param seperator The character which separates the dates.
//     *
//     * @param dates An array of dates. Note that each date must be in the format yyyy-MM-dd.
//     *
//     * @return A single string consisting of all dates separated by the separator.
//     */
//    static String convertDates (
//        String method,
//        String seperator,
//        String... dates
//    ) {
//        StringBuffer buffer = new StringBuffer();
//
//        if (dates != null && 0 < dates.length) {
//
//            for (int ctr = 0; ctr < dates.length; ctr++) {
//
//                assertDateFormatIsValid (method, dates[ctr]);
//
//                buffer.append(dates[ctr]);
//
//                if (ctr < dates.length - 1)
//                    buffer.append(seperator);
//            }
//        }
//
//        String result = (buffer.length() == 0) ? null : buffer.toString();
//
//        return result;
//    }

//    /**
//     * Method simply checks that the date format is valid and throws an exception if it isn't.
//     *
//     * @param method The method that was invoked.
//     *
//     * @param date For example 2001-10-20, which is valid, or X001-1-2, which is invalid.
//     *
//     * @throws InvalidDateFormatException Whenever the date is invalid.
//     */
//    static void assertDateFormatIsValid (String method, String date) {
//        Matcher matcher = DATE_PATTERN.matcher(date);
//
//        if (!matcher.matches())
//            throw new InvalidDateFormatException (
//                "The date parameter " + date +" passed to the method " + method + " is invalid.");
//    }

    @Override
    protected String getKey() {
        return getEscapedURI();
    }

    @Override
    protected <T> T doExecute(Class<T> type) {
        return (T) getRestTemplate ().getForObject(getEscapedURI (), type);
    }

    /**
     * Do get as {@link Shapes} and then return that result.
     */
    public Shapes doGetAsShapes () {

//        GsonFactoryBean gfb = new GsonFactoryBean ();
//        
//        
////        import org.springframework.http.converter.json.GsonBuilderUtils;
////        import org.springframework.http.converter.json.GsonHttpMessageConverter;
//        
//        GsonHttpMessageConverter ghmc = new GsonHttpMessageConverter ();
//        
//        ghmc.
//        
//        Gson gson = new GsonBuilder().registerTypeAdapter(Shapes.class, new ShapesDeserializer ()).create();
//        
//        
        return doGetAsShapes(data -> { return data; });
    }

    /**
     * Do get as {@link Shapes}, execute the given function, and then return an instance of type {@link Shapes}.
     */
    public Shapes doGetAsShapes (Function<Shapes, Shapes> function) {
        return doGetAsShapes(Shapes.class, function);
    }

    /**
     * Do get as {@link Shapes}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsShapes (Class<R> resultType, Function<Shapes, R> function) {

        Shapes shapes = doGet(Shapes.class);

        R result = function.apply(shapes);

        return result;
    }

    /**
     * Do get as {@link SeriesData} and then return that result.
     */
    public SeriesData doGetAsSeriesData () {
        return doGetAsSeriesData(data -> { return data; });
    }

    /**
     * Do get as {@link SeriesData}, execute the given function, and then return an instance of type {@link SeriesData}.
     */
    public SeriesData doGetAsSeriesData (Function<SeriesData, SeriesData> function) {
        return doGetAsSeriesData(SeriesData.class, function);
    }

    /**
     * Do get as {@link SeriesData}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsSeriesData (Class<R> resultType, Function<SeriesData, R> function) {

        SeriesData seriesData = doGet(SeriesData.class);

        R result = function.apply(seriesData);

        return result;
    }

    /**
     * Do get as {@link SeriesGroups} and then return that result.
     */
    public SeriesGroups doGetAsSeriesGroups () {
        return doGetAsSeriesGroups(data -> { return data; });
    }

    /**
     * Do get as {@link SeriesGroups}, execute the given function, and then return an instance of type
     * {@link SeriesGroups}.
     */
    public SeriesGroups doGetAsSeriesGroups (Function<SeriesGroups, SeriesGroups> function) {
        return doGetAsSeriesGroups(SeriesGroups.class, function);
    }

    /**
     * Do get as {@link SeriesGroups}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsSeriesGroups (Class<R> resultType, Function<SeriesGroups, R> function) {

        SeriesGroups SeriesGroups = doGet(SeriesGroups.class);

        R result = function.apply(SeriesGroups);

        return result;
    }
}