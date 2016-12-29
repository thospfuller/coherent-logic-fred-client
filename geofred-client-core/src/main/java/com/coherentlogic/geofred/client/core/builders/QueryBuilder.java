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
import com.coherentlogic.fred.client.domain.AggregationMethod;
import com.coherentlogic.fred.client.domain.FileType;
import com.coherentlogic.fred.client.domain.Frequency;
import com.coherentlogic.geofred.client.core.domain.RegionType;
import com.coherentlogic.geofred.client.core.domain.Season;
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
        SERIES_GROUP = "series_group",
        REGION_TYPE = "region_type",
        SEASON = "season",
        TRANSFORMATION = "transformation",
        SOURCE_ID = "source_id",
        SEARCH_TEXT = "search_text",
        SEARCH_TYPE = "search_type",
        SHAPES = "shapes",
        SHAPE = "shape",
        FILE = "file",
        SEMICOLON = ";",
        GEOFRED_API_ENTRY_POINT = "https://api.stlouisfed.org/geofred",
        SERIES = "series",
        REGIONAL = "regional",
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
     * Extends the path with shapes/file/ -- ie.
     *
     * https://api.stlouisfed.org/geofred/shapes/file?shape=bea&api_key=[TBD]
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html">Shapes</a>
     */
    public QueryBuilder shapesFile () {

        extendPathWith(SHAPES);
        extendPathWith(FILE);

        return this;
    }

    /**
     * Extends the path with series/group/.
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
     * Extends the path with series/data/.
     *
     * Note that the {@link #withFileTypeAsJSON()} method is called from within this method.
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
     * Extends the path with regional/data/.
     *
     * Note that the {@link #withFileTypeAsJSON()} method is called from within this method.
     *
     * https://api.stlouisfed.org/geofred/regional/data?api_key=[TBD]&series_group=1348&date=2013-01-01&region_type=state&units=Dollars&frequency=a&season=NSA
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#series_group">GeoFRED® API - Regional Data</a>
     */
    public QueryBuilder regionalData () {

        extendPathWith(REGIONAL);
        extendPathWith(DATA);

        return withFileTypeAsJSON();
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
     * The FRED series id you want to request GeoFRED meta information for; not all series that are in FRED have
     * geographical data.
     *
     * @param seriesId For example, "WIPCPI".
     */
    public QueryBuilder withSeriesId (String seriesId) {

        addParameter(SERIES_ID, seriesId);

        return this;
    }

    /**
     * The ID for a group of seriess found in GeoFRED.
     *
     * @param seriesGroup For example, "1348".
     */
    public QueryBuilder withSeriesGroup (String seriesGroup) {

        addParameter(SERIES_GROUP, seriesGroup);

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

    /**
     * The units of the series you want to pull.
     */
    public QueryBuilder withUnits (String units) {

        addParameter(UNITS, units);

        return this;
    }

    /**
     * The seasonality of the series group.
     *
     * Required, two possible SA (Seasonally Adjusted) or NSA (Not Seasonally Adjusted)
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#season">Season</a>
     */
    public QueryBuilder withSeason(Season season) {

        addParameter(SEASON, season);

        return this;
    }

    /**
     * Seasonally Adjusted
     *
     * @see {@link #withSeason(Season)}
     */
    public QueryBuilder withSeasonAsSA() {
        return withSeason (Season.NSA);
    }

    /**
     * Not Seasonally Adjusted
     *
     * @see {@link #withSeason(Season)}
     */
    public QueryBuilder withSeasonAsNSA() {
        return withSeason (Season.NSA);
    }

    /**
     * An optional parameter that indicates a lower frequency to aggregate values to. The GeoFRED frequency aggregation
     * feature converts higher frequency data series into lower frequency data series (e.g. converts a monthly data
     * series into an annual data series). In GeoFRED, the highest frequency data is daily, and the lowest frequency
     * data is annual.
     * <p>
     * There are 3 aggregation methods available- average, sum, and end of period.
     * <p>
     * Default: no value for no frequency aggregation
     * <p>
     * One of the following values: 'd', 'w', 'bw', 'm', 'q', 'sa', 'a', 'wef', 'weth', 'wew', 'wetu', 'wem', 'wesu',
     * 'wesa', 'bwew', 'bwem'
     * <p>
     * Frequencies without period descriptions:
     * <p>
     * d = Daily
     * w = Weekly
     * bw = Biweekly
     * m = Monthly
     * q = Quarterly
     * sa = Semiannual
     * a = Annual 
     *
     * @see {@link com.coherentlogic.fred.client.core.domain.Frequency}
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#aggregation_method">aggregation_method</a>
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#frequency">Frequency</a>
     */
    public QueryBuilder withFrequency (Frequency frequency) {

        addParameter(FREQUENCY, frequency);

        return this;
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsDaily () {
        return withFrequency(Frequency.d);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsWeekly () {
        return withFrequency(Frequency.w);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsBiweekly () {
        return withFrequency(Frequency.bw);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsMonthly () {
        return withFrequency(Frequency.m);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsQuarterly () {
        return withFrequency(Frequency.q);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsSemiannually () {
        return withFrequency(Frequency.sa);
    }

    /**
     * @see {@link #withFrequency(Frequency)}
     */
    public QueryBuilder withFrequencyAsAnnually () {
        return withFrequency(Frequency.a);
    }

    /**
     * The region you want want to pull data for.
     *
     * One of the following values:
     * bea
     * msa
     * frb
     * necta
     * state
     * country
     * county
     * censusregion
     * censusregion
     *
     * @see {@link com.coherentlogic.fred.client.core.domain.RegionType}
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">region_type</a>
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html">Regional Data</a>
     */
    public QueryBuilder withRegionType (RegionType regionType) {

        addParameter(REGION_TYPE, regionType);

        return this;
    }

    /**
     * Bureau of Economic Analysis Region
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsBEA () {
        return withRegionType (RegionType.bea);
    }

    /**
     * Metropolitan Statistical Area
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsMSA () {
        return withRegionType (RegionType.msa);
    }

    /**
     * Federal Reserve Bank Districts
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsFRB () {
        return withRegionType (RegionType.frb);
    }

    /**
     * New England City and Town Area
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsNECTA () {
        return withRegionType (RegionType.necta);
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsState () {
        return withRegionType (RegionType.state);
    }

    /**
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsCountry () {
        return withRegionType (RegionType.country);
    }

    /**
     * USA Counties
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsCounty () {
        return withRegionType (RegionType.county);
    }

    /**
     * US Census Regions
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsCensusRegion () {
        return withRegionType (RegionType.censusregion);
    }

    /**
     * US Census Divisons
     *
     * NOTE: Not sure about this value as the documentation has censusregion listed twice and the shape has this value
     * (in fact the available shapes are, aside from this value, the same as the region types).
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/shapes.html#shape">Shape</a>
     *
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#region_type">Region Type</a>
     */
    public QueryBuilder withRegionTypeAsCensusDivision () {
        return withRegionType (RegionType.censusdivision);
    }

    /**
     * string, optional, default: avg
     *
     * One of the following values: 'avg', 'sum', 'eop'
     *
     * avg = Average 
     * sum = Sum 
     * eop = End of Period
     *
     * @see com.coherentlogic.fred.client.core.domain.AggregationMethod
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#aggregation_method">aggregation_method</a>
     */
    public QueryBuilder withAggregationMethod (AggregationMethod aggregationMethod) {

        addParameter(AGGREGATION_METHOD, aggregationMethod);

        return this;
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     */
    public QueryBuilder withAggregationMethodAsAverage () {
        return withAggregationMethod(AggregationMethod.avg);
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     */
    public QueryBuilder withAggregationMethodAsSum () {
        return withAggregationMethod(AggregationMethod.sum);
    }

    /**
     * @see {@link #withAggregationMethod(AggregationMethod)}
     */
    public QueryBuilder withAggregationMethodAsEndOfPeriod () {
        return withAggregationMethod(AggregationMethod.eop);
    }

    /**
     * A key or file extension that indicates the type of file to send.
     *
     * Note that the GeoFRED QueryBuilder will set the file_type to JSON automatically.
     *
     * string, optional, default: xml
     *
     * One of the following values: 'xml', 'json'
     *
     * xml = Extensible Markup Language. The HTTP Content-Type is text/xml.
     * json = JavaScript Object Notation. The HTTP Content-Type is application/json.
     *
     * @see com.coherentlogic.fred.client.core.domain.FileType
     * @see <a href="https://research.stlouisfed.org/docs/api/geofred/regional_data.html#file_type">file_type</a>
     */
    protected QueryBuilder withFileType (String fileType) {

        addParameter(FILE_TYPE, fileType);

        return this;
    }

    /**
     * @see {@link #withFileType(String)}
     */
    protected QueryBuilder withFileType (FileType fileType) {
        return withFileType (fileType.toString());
    }

    /**
     * @see {@link #withFileType(String)}
     */
    protected QueryBuilder withFileTypeAsXML() {
        return withFileType(FileType.xml);
    }

    /**
     * @see {@link #withFileType(String)}
     */
    protected QueryBuilder withFileTypeAsJSON() {
        return withFileType(FileType.json);
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
