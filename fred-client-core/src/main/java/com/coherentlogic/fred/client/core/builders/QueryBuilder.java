package com.coherentlogic.fred.client.core.builders;

import static com.coherentlogic.fred.client.core.util.Constants.CATEGORIES;
import static com.coherentlogic.fred.client.core.util.Constants.CATEGORY;
import static com.coherentlogic.fred.client.core.util.Constants.CHILDREN;
import static com.coherentlogic.fred.client.core.util.Constants.DATES;
import static com.coherentlogic.fred.client.core.util.Constants.DATE_FORMAT;
import static com.coherentlogic.fred.client.core.util.Constants.DATE_PATTERN;
import static com.coherentlogic.fred.client.core.util.Constants.FRED;
import static com.coherentlogic.fred.client.core.util.Constants.GEOFRED;
import static com.coherentlogic.fred.client.core.util.Constants.OBSERVATIONS;
import static com.coherentlogic.fred.client.core.util.Constants.RELATED;
import static com.coherentlogic.fred.client.core.util.Constants.RELEASE;
import static com.coherentlogic.fred.client.core.util.Constants.RELEASES;
import static com.coherentlogic.fred.client.core.util.Constants.SEARCH;
import static com.coherentlogic.fred.client.core.util.Constants.SERIES;
import static com.coherentlogic.fred.client.core.util.Constants.SERIES_SEARCH_TEXT;
import static com.coherentlogic.fred.client.core.util.Constants.SOURCE;
import static com.coherentlogic.fred.client.core.util.Constants.SOURCES;
import static com.coherentlogic.fred.client.core.util.Constants.TAGS;
import static com.coherentlogic.fred.client.core.util.Constants.TAG_GROUP_ID;
import static com.coherentlogic.fred.client.core.util.Constants.TAG_NAMES;
import static com.coherentlogic.fred.client.core.util.Constants.TAG_SEARCH_TEXT;
import static com.coherentlogic.fred.client.core.util.Constants.UPDATES;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;
import java.util.regex.Matcher;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.coherent.data.adapter.core.builders.rest.AbstractRESTQueryBuilder;
import com.coherentlogic.coherent.data.adapter.core.cache.CacheServiceProviderSpecification;
import com.coherentlogic.coherent.data.adapter.core.util.WelcomeMessage;
import com.coherentlogic.fred.client.core.domain.AggregationMethod;
import com.coherentlogic.fred.client.core.domain.Categories;
import com.coherentlogic.fred.client.core.domain.FileType;
import com.coherentlogic.fred.client.core.domain.FilterValue;
import com.coherentlogic.fred.client.core.domain.FilterVariable;
import com.coherentlogic.fred.client.core.domain.Frequency;
import com.coherentlogic.fred.client.core.domain.Observations;
import com.coherentlogic.fred.client.core.domain.OrderBy;
import com.coherentlogic.fred.client.core.domain.OutputType;
import com.coherentlogic.fred.client.core.domain.ReleaseDates;
import com.coherentlogic.fred.client.core.domain.Releases;
import com.coherentlogic.fred.client.core.domain.SearchType;
import com.coherentlogic.fred.client.core.domain.Seriess;
import com.coherentlogic.fred.client.core.domain.SortOrder;
import com.coherentlogic.fred.client.core.domain.Sources;
import com.coherentlogic.fred.client.core.domain.TagGroupId;
import com.coherentlogic.fred.client.core.domain.Tags;
import com.coherentlogic.fred.client.core.domain.Unit;
import com.coherentlogic.fred.client.core.domain.VintageDates;
import com.coherentlogic.fred.client.core.exceptions.DateOutOfBoundsException;
import com.coherentlogic.fred.client.core.exceptions.InvalidDateFormatException;
import com.coherentlogic.fred.client.core.exceptions.InvalidParameterValue;
import com.coherentlogic.fred.client.core.exceptions.LimitOutOfBoundsException;
import com.coherentlogic.fred.client.core.exceptions.OffsetOutOfBoundsException;
import com.coherentlogic.fred.client.core.services.GoogleAnalyticsMeasurementService;

/**
 * Class that allows the developer to construct and execute a query to the Federal Reserve Bank of St. Louis' FRED web
 * services.
 * <p>
 * Note that this class is <b>not</b> thread-safe and cannot be used as a member-level property -- if this is required,
 * use the {@link com.coherentlogic.fred.client.core.factories.QueryBuilderFactory QueryBuilderFactory} class.
 * <p>
 * In order to facilitate method-chaining each setter method returns a reference
 * to this object.
 * <p>
 * For examples, refer to the QueryBuilderTest class.
 *
 * @author <a href="https://www.linkedin.com/in/thomasfuller">Thomas P. Fuller</a>
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class QueryBuilder extends AbstractRESTQueryBuilder<String> {

    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    static final String[] WELCOME_MESSAGE = {
        "*************************************************************************************************************",
        "***                                                                                                       ***",
        "***                                      Welcome to the FRED Client                                       ***",
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
            "FRED Client");

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
//        FILE_TYPE = "file_type",
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
        FRED_API_ENTRY_POINT = "https://api.stlouisfed.org/fred";

    private static final Calendar MIN_DATE_CALENDAR, MAX_DATE_CALENDAR;

    static {
        MIN_DATE_CALENDAR = Calendar.getInstance();
        MIN_DATE_CALENDAR.set(Calendar.YEAR, 1776);
        MIN_DATE_CALENDAR.set(Calendar.MONTH, Calendar.JULY);
        MIN_DATE_CALENDAR.set(Calendar.DAY_OF_MONTH, 04);

        MAX_DATE_CALENDAR = Calendar.getInstance();
        MAX_DATE_CALENDAR.set(Calendar.YEAR, 9999);
        MAX_DATE_CALENDAR.set(Calendar.MONTH, Calendar.DECEMBER);
        MAX_DATE_CALENDAR.set(Calendar.DAY_OF_MONTH, 31);
    }

    /**
     * 
     * @return 9999-12-31
     */
    public static Calendar getMaxDateCalendar() {
        return (Calendar) MAX_DATE_CALENDAR.clone();
    }

    /**
     * 
     * @return 1776-07-04
     */
    public static Calendar getMinDateCalendar() {
        return (Calendar) MIN_DATE_CALENDAR.clone();
    }

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
        super (restTemplate, FRED_API_ENTRY_POINT);
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
        super(restTemplate, FRED_API_ENTRY_POINT, cache);
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

//    /**
//     * Extends the path to include fred -- for example:
//     *
//     * https://api.stlouisfed.org/fred/
//     */
//    public QueryBuilder fred () {
//        extendPathWith(FRED);
//
//        return this;
//    }
//
//    /**
//     * Extends the path to include geofred -- for example:
//     *
//     * https://api.stlouisfed.org/geofred/
//     */
//    public QueryBuilder geofred () {
//        extendPathWith(GEOFRED);
//
//        return this;
//    }

    /**
     * Extends the path to include series -- for example:
     *
     * https://api.stlouisfed.org/fred/series
     */
    public QueryBuilder series () {
        extendPathWith(SERIES);

        return this;
    }

    /**
     * Extends the path to include categories -- for example:
     *
     * https://api.stlouisfed.org/fred/categories
     */
    public QueryBuilder categories () {
        extendPathWith(CATEGORIES);

        return this;
    }

    /**
     * Extends the path to include category -- for example:
     *
     * https://api.stlouisfed.org/fred/category
     */
    public QueryBuilder category () {
        extendPathWith(CATEGORY);

        return this;
    }

    /**
     * Extends the path to include observations -- for example:
     *
     * https://api.stlouisfed.org/fred/observations
     */
    public QueryBuilder observations () {
        extendPathWith(OBSERVATIONS);

        return this;
    }

    /**
     * Extends the path to include releases -- for example:
     *
     * https://api.stlouisfed.org/fred/releases
     */
    public QueryBuilder releases () {
        extendPathWith(RELEASES);

        return this;
    }

    /**
     * Extends the path to include release -- for example:
     *
     * https://api.stlouisfed.org/fred/release
     */
    public QueryBuilder release () {
        extendPathWith(RELEASE);

        return this;
    }

    /**
     * Extends the path to include updates -- for example:
     *
     * https://api.stlouisfed.org/fred/updates
     */
    public QueryBuilder updates () {
        extendPathWith(UPDATES);

        return this;
    }

    /**
     * Extends the path to include vintagedates -- for example:
     *
     * https://api.stlouisfed.org/fred/vintagedates
     */
    public QueryBuilder vintageDates () {
        extendPathWith(VINTAGE_DATES);

        return this;
    }

    /**
     * Extends the path to include search -- for example:
     *
     * https://api.stlouisfed.org/fred/search
     */
    public QueryBuilder search () {
        extendPathWith(SEARCH);

        return this;
    }

    /**
     * Extends the path to include children -- for example:
     *
     * https://api.stlouisfed.org/fred/children
     */
    public QueryBuilder children () {
        extendPathWith(CHILDREN);

        return this;
    }

    /**
     * Extends the path to include related -- for example:
     *
     * https://api.stlouisfed.org/fred/related
     */
    public QueryBuilder related () {
        extendPathWith(RELATED);

        return this;
    }

    /**
     * Extends the path to include sources -- for example:
     *
     * https://api.stlouisfed.org/fred/sources
     */
    public QueryBuilder sources () {
        extendPathWith(SOURCES);

        return this;
    }

    /**
     * Extends the path to include source -- for example:
     *
     * https://api.stlouisfed.org/fred/source
     */
    public QueryBuilder source () {
        extendPathWith(SOURCE);

        return this;
    }

    /**
     * Extends the path to include dates -- for example:
     *
     * https://api.stlouisfed.org/fred/dates
     */
    public QueryBuilder dates () {
        extendPathWith(DATES);

        return this;
    }

    /**
     * Extends the path to include tags -- for example:
     *
     * https://api.stlouisfed.org/fred/tags
     */
    public QueryBuilder tags () {
        extendPathWith(TAGS);

        return this;
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
     * Setter method for the series id parameter.
     *
     * @param seriesId For example, "GNPCA".
     */
    public QueryBuilder withSeriesId (String seriesId) {

        addParameter(SERIES_ID, seriesId);

        return this;
    }

    /**
     * Setter method for the release id parameter.
     *
     * @param releaseId For example, 53L.
     */
    public QueryBuilder withReleaseId (long releaseId) {

        addParameter(RELEASE_ID, Long.toString(releaseId));

        return this;
    }

    /**
     * Setter method for the category id parameter.
     */
    public QueryBuilder withCategoryId (long categoryId) {

        addParameter(CATEGORY_ID, Long.toString(categoryId));

        return this;
    }

    /**
     * Setter method for the source id parameter.
     */
    public QueryBuilder withSourceId (long sourceId) {

        addParameter(SOURCE_ID, Long.toString(sourceId));

        return this;
    }

    /**
     * Setter method for the real-time start date parameter.
     */
    public QueryBuilder withRealtimeStart (Date realtimeStart) {

        boolean between = isBetween (realtimeStart);

        if (!between)
            throw new DateOutOfBoundsException(realtimeStart);

        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);

        String realtimeStartText = dateFormat.format(realtimeStart);

        addParameter(REALTIME_START, realtimeStartText);

        return this;
    }

    /**
     * Setter method for the real-time start date parameter.
     *
     * @throws InvalidDateFormatException When the date does not conform to the expected format
     *  (ie. yyyy-MM-dd / 2012-06-21).
     */
    public QueryBuilder withRealtimeStart (String realtimeStart) {

        assertDateFormatIsValid ("realtimeStart", realtimeStart);

        addParameter(REALTIME_START, realtimeStart);

        return this;
    }

    /**
     * Setter method for the real-time end date parameter.
     */
    public QueryBuilder withRealtimeEnd (Date realtimeEnd) {

        boolean between = isBetween (realtimeEnd);

        if (!between)
            throw new DateOutOfBoundsException(realtimeEnd);

        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);

        String realtimeEndText = dateFormat.format(realtimeEnd);

        addParameter(REALTIME_END, realtimeEndText);

        return this;
    }

    /**
     * Setter method for the real-time end date parameter.
     *
     * @throws InvalidDateFormatException When the date does not conform to the expected format
     *  (ie. yyyy-MM-dd / 2012-06-21).
     */
    public QueryBuilder withRealtimeEnd (String realtimeEnd) {

        assertDateFormatIsValid ("realtimeEnd", realtimeEnd);

        addParameter(REALTIME_END, realtimeEnd);

        return this;
    }

    /**
     * Setter method for the limit parameter.
     *
     * @param limit A value between 1 and 100000 (inclusive).
     */
    public QueryBuilder withLimit (long limit) {

        if (!(1 <= limit && limit <= 100000))
            throw new LimitOutOfBoundsException(limit);

        addParameter(LIMIT, Long.toString(limit));

        return this;
    }

    /**
     * Setter method for the offset parameter.
     *
     * @param offset A non-negative integer.
     *
     * @throws OffsetOutOfBoundsException if the value of the offset is less than zero.
     */
    public QueryBuilder withOffset (int offset) {

        if (offset < 0)
            throw new OffsetOutOfBoundsException (offset);

        addParameter(OFFSET, Integer.toString(offset));

        return this;
    }

    /**
     * Setter method for the order-by parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.OrderBy
     */
    public QueryBuilder withOrderBy (OrderBy orderBy) {

        addParameter(ORDER_BY, orderBy);

        return this;
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#searchRank}
     */
    public QueryBuilder withOrderByAsSearchRank () {
        return withOrderBy (OrderBy.searchRank);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#seriesId}
     */
    public QueryBuilder withOrderByAsSeriesId () {
        return withOrderBy (OrderBy.seriesId);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#sourceId}
     */
    public QueryBuilder withOrderByAsSourceId () {
        return withOrderBy (OrderBy.sourceId);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#releaseId}
     */
    public QueryBuilder withOrderByAsReleaseId () {
        return withOrderBy (OrderBy.releaseId);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#releaseDate}
     */
    public QueryBuilder withOrderByAsReleaseDate () {
        return withOrderBy (OrderBy.releaseDate);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#title}
     */
    public QueryBuilder withOrderByAsTitle () {
        return withOrderBy (OrderBy.title);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#units}
     */
    public QueryBuilder withOrderByAsUnits () {
        return withOrderBy (OrderBy.units);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#frequency}
     */
    public QueryBuilder withOrderByAsFrequency () {
        return withOrderBy (OrderBy.frequency);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#seasonalAdjustment}
     */
    public QueryBuilder withOrderByAsSeasonalAdjustment () {
        return withOrderBy (OrderBy.seasonalAdjustment);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#realtimeStart}
     */
    public QueryBuilder withOrderByAsRealtimeStart () {
        return withOrderBy (OrderBy.realtimeStart);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#realtimeEnd}
     */
    public QueryBuilder withOrderByAsRealtimeEnd () {
        return withOrderBy (OrderBy.realtimeEnd);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#lastUpdated}
     */
    public QueryBuilder withOrderByAsLastUpdated () {
        return withOrderBy (OrderBy.lastUpdated);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#observationStart}
     */
    public QueryBuilder withOrderByAsObservationStart () {
        return withOrderBy (OrderBy.observationStart);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#observationEnd}
     */
    public QueryBuilder withOrderByAsObservationEnd () {
        return withOrderBy (OrderBy.observationEnd);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#popularity}
     */
    public QueryBuilder withOrderByAsPopularity () {
        return withOrderBy (OrderBy.popularity);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#vintageDate}
     */
    public QueryBuilder withOrderByAsVintageDate () {
        return withOrderBy (OrderBy.vintageDate);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#seriesCount}
     */
    public QueryBuilder withOrderByAsSeriesCount () {
        return withOrderBy (OrderBy.seriesCount);
    }

    /**
     * @see {@link #withOrderBy(OrderBy)}
     * @see {@link OrderBy#observationDate}
     */
    public QueryBuilder withOrderByAsObservationDate () {
        return withOrderBy (OrderBy.observationDate);
    }

    /**
     * Setter method for the sort order parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.SortOrder
     */
    public QueryBuilder withSortOrder (SortOrder sortOrder) {

        addParameter(SORT_ORDER, sortOrder);

        return this;
    }

    /**
     * @see {@link #withSortOrder(SortOrder)}
     */
    public QueryBuilder withSortOrderAsAsc () {
        return withSortOrder(SortOrder.asc);
    }

    /**
     * @see {@link #withSortOrder(SortOrder)}
     */
    public QueryBuilder withSortOrderAsDesc () {
        return withSortOrder(SortOrder.desc);
    }

    /**
     * Setter method for the filter variable parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.FilterVariable
     */
    public QueryBuilder withFilterVariable (FilterVariable filterVariable) {

        addParameter(FILTER_VARIABLE, filterVariable);

        return this;
    }

    /**
     * @see {@link #withFilterVariable(FilterVariable)}
     */
    public QueryBuilder withFilterVariableAsFrequency () {
        return withFilterVariable(FilterVariable.frequency);
    }

    /**
     * @see {@link #withFilterVariable(FilterVariable)}
     */
    public QueryBuilder withFilterVariableAsUnits () {
        return withFilterVariable(FilterVariable.units);
    }

    /**
     * @see {@link #withFilterVariable(FilterVariable)}
     */
    public QueryBuilder withFilterVariableAsSeasonalAdjustment () {
        return withFilterVariable(FilterVariable.seasonalAdjustment);
    }

    /**
     * @see {@link #withFilterVariable(FilterVariable)}
     */
    public QueryBuilder withFilterVariableAsGeography () {
        return withFilterVariable(FilterVariable.geography);
    }

    /**
     * Setter method for the filter value parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.FilterValue
     */
    public QueryBuilder withFilterValue (FilterValue filterValue) {

        addParameter(FILTER_VALUE, filterValue);

        return this;
    }

    /**
     * @see {@link #withFilterValue(FilterValue)}
     * @see {@link FilterValue#all}
     */
    public QueryBuilder withFilterValueAsAll () {
        return withFilterValue (FilterValue.all);
    }

    /**
     * @see {@link #withFilterValue(FilterValue)}
     * @see {@link FilterValue#macro}
     */
    public QueryBuilder withFilterValueAsMacro () {
        return withFilterValue (FilterValue.macro);
    }

    /**
     * @see {@link #withFilterValue(FilterValue)}
     * @see {@link FilterValue#regional}
     */
    public QueryBuilder withFilterValueAsRegional () {
        return withFilterValue (FilterValue.regional);
    }

    /**
     * Setter method for the include-release-dates-with-no-data flag parameter.
     */
    public QueryBuilder withIncludeReleaseDatesWithNoData (boolean value) {

        addParameter(INCLUDE_RELEASE_DATES_WITH_NO_DATA, Boolean.toString(value));

        return this;
    }

    /**
     * Setter method for the observation start date parameter.
     */
    public QueryBuilder withObservationStart (Date observationStart) {

        boolean between = isBetween (observationStart);

        if (!between)
            throw new DateOutOfBoundsException(observationStart);

        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);

        String observationStartText = dateFormat.format(observationStart);

        addParameter(OBSERVATION_START, observationStartText);

        return this;
    }

    /**
     * Setter method for the observation start date parameter.
     *
     * @throws InvalidDateFormatException When the date does not conform to the expected format
     *  (ie. yyyy-MM-dd / 2012-06-21).
     */
    public QueryBuilder withObservationStart (String observationStart) {

        assertDateFormatIsValid ("observationStart", observationStart);

        addParameter(OBSERVATION_START, observationStart);

        return this;
    }

    /**
     * Setter method for the observation end date parameter.
     *
     * @param observationEnd
     */
    public QueryBuilder withObservationEnd (Date observationEnd) {

        boolean between = isBetween (observationEnd);

        if (!between)
            throw new DateOutOfBoundsException(observationEnd);

        DateFormat dateFormat = new SimpleDateFormat (DATE_FORMAT);

        String observationEndText = dateFormat.format(observationEnd);

        addParameter(OBSERVATION_END, observationEndText);

        return this;
    }

    /**
     * Setter method for the observation end date parameter parameter.
     *
     * @throws InvalidDateFormatException When the date does not conform to the expected format
     *  (ie. yyyy-MM-dd / 2012-06-21).
     */
    public QueryBuilder withObservationEnd (String observationEnd) {

        assertDateFormatIsValid ("observationEnd", observationEnd);

        addParameter(OBSERVATION_END, observationEnd);

        return this;
    }

    /**
     * Setter method for the units parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.Unit
     */
    public QueryBuilder withUnits (Unit unit) {

        addParameter(UNITS, unit);

        return this;
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#lin}
     */
    public QueryBuilder withUnitsAsLin () {
        return withUnits (Unit.lin);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#chg}
     */
    public QueryBuilder withUnitsAsChg () {
        return withUnits (Unit.chg);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#ch1}
     */
    public QueryBuilder withUnitsAsCh1 () {
        return withUnits (Unit.ch1);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#pch}
     */
    public QueryBuilder withUnitsAsPch () {
        return withUnits (Unit.pch);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#pc1}
     */
    public QueryBuilder withUnitsAsPc1 () {
        return withUnits (Unit.pc1);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#pca}
     */
    public QueryBuilder withUnitsAsPca () {
        return withUnits (Unit.pca);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#cch}
     */
    public QueryBuilder withUnitsAsCch () {
        return withUnits (Unit.cch);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#cca}
     */
    public QueryBuilder withUnitsAsCca () {
        return withUnits (Unit.cca);
    }

    /**
     * @see {@link #withUnits(Units)}
     * @see {@link Unit#log}
     */
    public QueryBuilder withUnitsAsLog () {
        return withUnits (Unit.log);
    }

    /**
     * Setter method for the frequency parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.Frequency
     */
    public QueryBuilder withFrequency (Frequency frequency) {

        addParameter(FREQUENCY, frequency);

        return this;
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#d}
     */
    public QueryBuilder withFrequencyAsDaily () {
        return withFrequency (Frequency.d);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#w}
     */
    public QueryBuilder withFrequencyAsWeekly () {
        return withFrequency (Frequency.w);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#bw}
     */
    public QueryBuilder withFrequencyAsBiweekly () {
        return withFrequency (Frequency.bw);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#m}
     */
    public QueryBuilder withFrequencyAsMonthly () {
        return withFrequency (Frequency.m);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#q}
     */
    public QueryBuilder withFrequencyAsQuarterly () {
        return withFrequency (Frequency.q);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#sa}
     */
    public QueryBuilder withFrequencyAsSemiAnnually () {
        return withFrequency (Frequency.sa);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#a}
     */
    public QueryBuilder withFrequencyAsAnnually () {
        return withFrequency (Frequency.a);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wef}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingFriday () {
        return withFrequency (Frequency.wef);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#weth}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingThursday () {
        return withFrequency (Frequency.weth);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wew}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingWednesday () {
        return withFrequency (Frequency.wew);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wetu}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingTuesday () {
        return withFrequency (Frequency.wetu);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wem}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingMonday () {
        return withFrequency (Frequency.wem);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wesu}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingSunday () {
        return withFrequency (Frequency.wesu);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#wesa}
     */
    public QueryBuilder withFrequencyAsWeeklyEndingSaturday () {
        return withFrequency (Frequency.wesa);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#bwew}
     */
    public QueryBuilder withFrequencyAsBiweeklyEndingWednesday () {
        return withFrequency (Frequency.bwew);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     * @see {@link Frequency#bwem}
     */
    public QueryBuilder withFrequencyAsBiweeklyEndingMonday () {
        return withFrequency (Frequency.bwem);
    }

    /**
     * Setter method for the aggregation method parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.AggregationMethod
     */
    public QueryBuilder withAggregationMethod (AggregationMethod aggregationMethod) {

        addParameter(AGGREGATION_METHOD, aggregationMethod);

        return this;
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     * @see {@link AggregationMethod#avg}
     */
    public QueryBuilder withAggregationMethodAsAvg () {
        return withAggregationMethod (AggregationMethod.avg);
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     * @see {@link AggregationMethod#sum}
     */
    public QueryBuilder withAggregationMethodAsSum () {
        return withAggregationMethod (AggregationMethod.sum);
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     * @see {@link AggregationMethod#eop}
     */
    public QueryBuilder withAggregationMethodAsEop () {
        return withAggregationMethod (AggregationMethod.eop);
    }

    /**
     * Setter method for the output type parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.OutputType
     */
    public QueryBuilder withOutputType (OutputType outputType) {

        addParameter(OUTPUT_TYPE, outputType);

        return this;
    }

    /**
     * @see {@link #withOutputType(OutputType)}
     * @see {@link OutputType#observationsByRealTimePeriod}
     */
    public QueryBuilder withOutputTypeAsObservationsByRealTimePeriod () {
        return withOutputType (OutputType.observationsByRealTimePeriod);
    }

    /**
     * @see {@link #withOutputType(OutputType)}
     * @see {@link OutputType#observationsByVintageDateAllObservations}
     */
    public QueryBuilder withOutputTypeAsObservationsByVintageDateAllObservations () {
        return withOutputType (OutputType.observationsByVintageDateAllObservations);
    }

    /**
     * @see {@link #withOutputType(OutputType)}
     * @see {@link OutputType#observationsByVintageDateNewAndRevisedObservationsOnly}
     */
    public QueryBuilder withOutputTypeAsObservationsByVintageDateNewAndRevisedObservationsOnly () {
        return withOutputType (OutputType.observationsByVintageDateNewAndRevisedObservationsOnly);
    }

    /**
     * @see {@link #withOutputType(OutputType)}
     * @see {@link OutputType#observationsInitialReleaseOnly}
     */
    public QueryBuilder withOutputTypeAsObservationsInitialReleaseOnly () {
        return withOutputType (OutputType.observationsInitialReleaseOnly);
    }

    /**
     * Setter method for the file type parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.FileType
     */
    public QueryBuilder withFileType (String fileType) {

        addParameter(com.coherentlogic.fred.client.Constants.FILE_TYPE, fileType);

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

    /**
     * Setter method for the vinatage dates parameter, which can be a single date, or a list of dates separated by a
     * comma.
     *
     * Note that the format of the dates is not checked client-side -- if there is an invalid date, the server will
     * reject the call.
     */
    public QueryBuilder withVintageDates (String vintageDates) {

        addParameter(VINTAGE_DATES_PARAM, vintageDates);

        return this;
    }

    /**
     * Setter method for the array of vintage dates parameter.
     *
     * @throws InvalidDateFormatException If any of the dates are not of the format yyyy-MM-dd.
     */
    public QueryBuilder withVintageDates (String... vintageDates) {

        String value = convertDates ("setVintageDates", ",", vintageDates);

        addParameter(VINTAGE_DATES_PARAM, value);

        return this;
    }

    /**
     * Setter method for the search text  parameter which consists of the words to match against economic data series.
     */
    public QueryBuilder withSearchText (String searchText) {

        addParameter(SEARCH_TEXT, searchText);

        return this;
    }

    /**
     * Setter method for the series search text  parameter which consists of the words to match against economic data
     * series -- for example "monetary service index".
     */
    public QueryBuilder withSeriesSearchText (String seriesSearchText) {

        addParameter(SERIES_SEARCH_TEXT, seriesSearchText);

        return this;
    }

    /**
     * Setter method for the search type parameter.
     *
     * @see com.coherentlogic.fred.client.core.domain.SearchType
     */
    public QueryBuilder withSearchType (SearchType searchType) {

        addParameter(SEARCH_TYPE, searchType);

        return this;
    }

    /**
     * @see {@link #withSearchType(SearchType)}
     * @see {@link SearchType#fullText}
     */
    public QueryBuilder withSearchTypeAsFullText () {
        return withSearchType (SearchType.fullText);
    }

    /**
     * @see {@link #withSearchType(SearchType)}
     * @see {@link SearchType#seriesId}
     */
    public QueryBuilder withSearchTypeAsSeriesId () {
        return withSearchType (SearchType.seriesId);
    }

    /**
     * Method takes a single string, such as "slovenia;food;oecd", and set this as the tagName; this value filters
     * results to match either tag "slovenia", "food", or "oecd".
     *
     * @see <a href="https://api.stlouisfed.org/docs/fred/series_search_related_tags.html">Series search related tags</a>
     */
    public QueryBuilder withTagNames (String tagNames) {

        addParameter(TAG_NAMES, tagNames);

        return this;
    }

    /**
     * An array of tags to filter results by -- optional, no filtering by tags by default.
     *
     * For example: 'm1;m2' -- this value filters results to match either tag 'm1' or tag 'm2'.
     *
     * This method takes an array of strings, such as "slovenia", "food", "oecd" and creates a single aggregated string
     * with each value separated by a semicolon (ie. "slovenia;food;oecd").
     *
     * @see <a href="https://api.stlouisfed.org/docs/fred/series_search_related_tags.html">Series search related tags</a>
     */
    public QueryBuilder withTagNames (String... tagNames) {

        String result = combine (SEMICOLON, tagNames);

        return withTagNames (result);
    }

    /**
     * A tag group id to filter tags by type.
     *
     * String, optional, no filtering by tag group by default.
     * One of the following: 'freq', 'gen', 'geo', 'geot', 'rls', 'seas', 'src'.
     *
     * freq = Frequency
     * gen = General or Concept
     * geo = Geography
     * geot = Geography Type
     * rls = Release
     * seas = Seasonal Adjustment
     * src = Source
     */
    public QueryBuilder withTagGroupId (TagGroupId tagGroupId) {

        addParameter (TAG_GROUP_ID, tagGroupId);

        return this;
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#freq}
     */
    public QueryBuilder withTagGroupIdAsFrequency () {
        return withTagGroupId (TagGroupId.freq);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#gen}
     */
    public QueryBuilder withTagGroupIdAsGeneralOrConcept () {
        return withTagGroupId (TagGroupId.gen);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#geo}
     */
    public QueryBuilder withTagGroupIdAsGeography () {
        return withTagGroupId (TagGroupId.geo);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#geot}
     */
    public QueryBuilder withTagGroupIdAsGeographyType () {
        return withTagGroupId (TagGroupId.geot);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#rls}
     */
    public QueryBuilder withTagGroupIdAsRelease () {
        return withTagGroupId (TagGroupId.rls);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#seas}
     */
    public QueryBuilder withTagGroupIdAsSeasonalAdjustment () {
        return withTagGroupId (TagGroupId.seas);
    }

    /**
     * @see {@link #withTagGroupId(TagGroupId)}
     * @see {@link TagGroupId#src}
     */
    public QueryBuilder withTagGroupIdAsSource () {
        return withTagGroupId (TagGroupId.src);
    }

    /**
     * The words to find matching tags with -- optional, no filtering by search words by default.
     */
    public QueryBuilder withTagSearchText (String tagSearchText) {

        addParameter (TAG_SEARCH_TEXT, tagSearchText);

        return this;
    }

    static String combine (String seperator, String... values) {

        assertNotNullOrEmpty ("values", values);

        StringBuilder result = new StringBuilder ();

        int ctr = values.length;

        for (String next : values) {
            result.append(next);

            if (1 < ctr--) {
                result.append(seperator);
            }
        }
        return result.toString();
    }

    /**
     * Todo: Move this method to the parent package.
     */
    static void assertNotNullOrEmpty (String variableName, Object[] values) {
        if (values == null || values.length == 0)
            throw new InvalidParameterValue ("The variable named '" +
                variableName + "' is either null or empty (" +
                ToStringBuilder.reflectionToString(values) + ").");
    }

    /**
     * Method checks that the actual date is between the {@link #MIN_DATE_CALENDAR} and {@link #MAX_DATE_CALENDAR}
     * dates.
     *
     * @param actual The actual date.
     *
     * @return True if the actual date is between the {@link #MIN_DATE_CALENDAR} and {@link #MAX_DATE_CALENDAR} dates.
     */
    private boolean isBetween (Date actual) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actual);

        int minValue = calendar.compareTo(MIN_DATE_CALENDAR);
        int maxValue = calendar.compareTo(MAX_DATE_CALENDAR);

        boolean result = (0 <= minValue) && (maxValue <= 0);

        return result;
    }

    /**
     * Method converts the <i>dates</i> into a single String, with each date separated by a comma.
     *
     * @param method The name of the method calling this method; this is used when an exception is thrown because one of
     * the dates is in an invalid format.
     *
     * @param seperator The character which separates the dates.
     *
     * @param dates An array of dates. Note that each date must be in the format yyyy-MM-dd.
     *
     * @return A single string consisting of all dates separated by the separator.
     */
    static String convertDates (
        String method,
        String seperator,
        String... dates
    ) {
        StringBuffer buffer = new StringBuffer();

        if (dates != null && 0 < dates.length) {

            for (int ctr = 0; ctr < dates.length; ctr++) {

                assertDateFormatIsValid (method, dates[ctr]);

                buffer.append(dates[ctr]);

                if (ctr < dates.length - 1)
                    buffer.append(seperator);
            }
        }

        String result = (buffer.length() == 0) ? null : buffer.toString();

        return result;
    }

    /**
     * Method simply checks that the date format is valid and throws an exception if it isn't.
     *
     * @param method The method that was invoked.
     *
     * @param date For example 2001-10-20, which is valid, or X001-1-2, which is invalid.
     *
     * @throws InvalidDateFormatException Whenever the date is invalid.
     */
    static void assertDateFormatIsValid (String method, String date) {
        Matcher matcher = DATE_PATTERN.matcher(date);

        if (!matcher.matches())
            throw new InvalidDateFormatException (
                "The date parameter " + date +" passed to the method " + method + " is invalid.");
    }

    @Override
    protected String getKey() {
        return getEscapedURI();
    }

    @Override
    protected <T> T doExecute(Class<T> type) {
        return (T) getRestTemplate ().getForObject(getEscapedURI (), type);
    }

    /**
     * Do get as {@link Seriess} and then return that result.
     */
    public Seriess doGetAsSeriess () {
        return doGetAsSeriess(data -> { return data; });
    }

    /**
     * Do get as {@link Seriess}, execute the given function, and then return an instance of type {@link Seriess}.
     */
    public Seriess doGetAsSeriess (Function<Seriess, Seriess> function) {
        return doGetAsSeriess(Seriess.class, function);
    }

    /**
     * Do get as {@link Seriess}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsSeriess (Class<R> resultType, Function<Seriess, R> function) {

        Seriess seriess = doGet(Seriess.class);

        R result = function.apply(seriess);

        return result;
    }

    /**
     * Do get as {@link Categories} and then return that result.
     */
    public Categories doGetAsCategories () {
        return doGetAsCategories(data -> { return data; });
    }

    /**
     * Do get as {@link Categories}, execute the given function, and then return an instance of type {@link Categories}.
     */
    public Categories doGetAsCategories (Function<Categories, Categories> function) {
        return doGetAsCategories(Categories.class, function);
    }

    /**
     * Do get as {@link Categories}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsCategories (Class<R> resultType, Function<Categories, R> function) {

        Categories categories = doGet(Categories.class);

        R result = function.apply(categories);

        return result;
    }

    /**
     * Do get as {@link Observations} and then return that result.
     */
    public Observations doGetAsObservations () {
        return doGetAsObservations(data -> { return data; });
    }

    /**
     * Do get as {@link Observations}, execute the given function, and then return an instance of type
     * {@link Observations}.
     */
    public Observations doGetAsObservations (Function<Observations, Observations> function) {
        return doGetAsObservations(Observations.class, function);
    }

    /**
     * Do get as {@link Observations}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsObservations (Class<R> resultType, Function<Observations, R> function) {

        Observations observations = doGet(Observations.class);

        R result = function.apply(observations);

        return result;
    }

    /**
     * Do get as {@link Releases} and then return that result.
     */
    public Releases doGetAsReleases () {
        return doGetAsReleases(data -> { return data; });
    }

    /**
     * Do get as {@link Releases}, execute the given function, and then return an instance of type {@link Releases}.
     */
    public Releases doGetAsReleases (Function<Releases, Releases> function) {
        return doGetAsReleases(Releases.class, function);
    }

    /**
     * Do get as {@link Releases}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsReleases (Class<R> resultType, Function<Releases, R> function) {

        Releases releases = doGet(Releases.class);

        R result = function.apply(releases);

        return result;
    }

    /**
     * Do get as {@link VintageDates} and then return that result.
     */
    public VintageDates doGetAsVintageDates () {
        return doGetAsVintageDates(data -> { return data; });
    }

    /**
     * Do get as {@link VintageDates}, execute the given function, and then return an instance of type {@link VintageDates}.
     */
    public VintageDates doGetAsVintageDates (Function<VintageDates, VintageDates> function) {
        return doGetAsVintageDates(VintageDates.class, function);
    }

    /**
     * Do get as {@link VintageDates}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsVintageDates (Class<R> resultType, Function<VintageDates, R> function) {

        VintageDates vintageDates = doGet(VintageDates.class);

        R result = function.apply(vintageDates);

        return result;
    }

    /**
     * Do get as {@link Sources} and then return that result.
     */
    public Sources doGetAsSources () {
        return doGetAsSources(data -> { return data; });
    }

    /**
     * Do get as {@link Sources}, execute the given function, and then return an instance of type {@link Sources}.
     */
    public Sources doGetAsSources (Function<Sources, Sources> function) {
        return doGetAsSources(Sources.class, function);
    }

    /**
     * Do get as {@link Sources}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsSources (Class<R> resultType, Function<Sources, R> function) {

        Sources sources = doGet(Sources.class);

        R result = function.apply(sources);

        return result;
    }

    /**
     * Do get as {@link ReleaseDates} and then return that result.
     */
    public ReleaseDates doGetAsReleaseDates () {
        return doGetAsReleaseDates(data -> { return data; });
    }

    /**
     * Do get as {@link ReleaseDates}, execute the given function, and then return an instance of type
     * {@link ReleaseDates}.
     */
    public ReleaseDates doGetAsReleaseDates (Function<ReleaseDates, ReleaseDates> function) {
        return doGetAsReleaseDates(ReleaseDates.class, function);
    }

    /**
     * Do get as {@link ReleaseDates}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsReleaseDates (Class<R> resultType, Function<ReleaseDates, R> function) {

        ReleaseDates releaseDates = doGet(ReleaseDates.class);

        R result = function.apply(releaseDates);

        return result;
    }

    /**
     * Do get as {@link Tags} and then return that result.
     */
    public Tags doGetAsTags () {
        return doGetAsTags(data -> { return data; });
    }

    /**
     * Do get as {@link Tags}, execute the given function, and then return an instance of type {@link Tags}.
     */
    public Tags doGetAsTags (Function<Tags, Tags> function) {
        return doGetAsTags(Tags.class, function);
    }

    /**
     * Do get as {@link Tags}, execute the given function, and then return an instance of type resultType.
     */
    public <R> R doGetAsTags (Class<R> resultType, Function<Tags, R> function) {

        Tags tags = doGet(Tags.class);

        R result = function.apply(tags);

        return result;
    }
}
