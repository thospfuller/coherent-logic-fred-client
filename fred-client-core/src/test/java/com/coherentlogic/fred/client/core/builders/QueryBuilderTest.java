package com.coherentlogic.fred.client.core.builders;

import static com.coherentlogic.fred.client.Constants.FILE_TYPE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.domain.AggregationMethod;
import com.coherentlogic.fred.client.core.domain.FileType;
import com.coherentlogic.fred.client.core.domain.FilterValue;
import com.coherentlogic.fred.client.core.domain.FilterVariable;
import com.coherentlogic.fred.client.core.domain.Frequency;
import com.coherentlogic.fred.client.core.domain.OrderBy;
import com.coherentlogic.fred.client.core.domain.OutputType;
import com.coherentlogic.fred.client.core.domain.SearchType;
import com.coherentlogic.fred.client.core.domain.SortOrder;
import com.coherentlogic.fred.client.core.domain.TagGroupId;
import com.coherentlogic.fred.client.core.domain.Unit;
import com.coherentlogic.fred.client.core.exceptions.InvalidDateFormatException;
import com.coherentlogic.fred.client.core.exceptions.InvalidParameterValue;
import com.coherentlogic.fred.client.core.exceptions.LimitOutOfBoundsException;
import com.coherentlogic.fred.client.core.exceptions.OffsetOutOfBoundsException;

/**
 * Unit test for the QueryBuilder class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class QueryBuilderTest {

    private Calendar calendar = null;

    private QueryBuilder builder = null;

    private static final String PRE = "/?",
        VALID_DATE = "1981-05-21",
        INVALID_DATE = "1X81-05-21",
        RANDOM_TEXT = "ABC";

    private static final int RANDOM_INT = 123;

    @Before
    public void setUp() throws Exception {
        calendar = Calendar.getInstance();
        builder = new QueryBuilder (null, "/");
    }

    @After
    public void tearDown() throws Exception {
        calendar = null;
        builder = null;
    }

    private static String format (String key, String value) {
        return PRE + key + "=" + value;
    }

    private static String format (String key, int value) {
        return format (key, "" + value);
    }

    @Test
    public void testApiKey() {
        String apiKey = RANDOM_TEXT;

        builder.withApiKey(apiKey);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.API_KEY, apiKey), actual);
    }

    @Test
    public void series () {
        builder.series();

        assertEquals ("/series", builder.getEscapedURI());
    }

    @Test
    public void categories () {
        builder.categories();

        assertEquals ("/categories", builder.getEscapedURI());
    }

    @Test
    public void category () {
        builder.series();

        assertEquals ("/series", builder.getEscapedURI());
    }

    @Test
    public void observations () {
        builder.observations();

        assertEquals ("/observations", builder.getEscapedURI());
    }

    @Test
    public void releases () {
        builder.releases();

        assertEquals ("/releases", builder.getEscapedURI());
    }

    @Test
    public void release () {
        builder.release();

        assertEquals ("/release", builder.getEscapedURI());
    }

    @Test
    public void updates () {
        builder.updates();

        assertEquals ("/updates", builder.getEscapedURI());
    }

    @Test
    public void vintageDates () {
        builder.vintageDates();

        assertEquals ("/vintagedates", builder.getEscapedURI());
    }

    @Test
    public void search () {
        builder.search();

        assertEquals ("/search", builder.getEscapedURI());
    }

    @Test
    public void children () {
        builder.children();

        assertEquals ("/children", builder.getEscapedURI());
    }

    @Test
    public void related () {
        builder.related();

        assertEquals ("/related", builder.getEscapedURI());
    }

    @Test
    public void sources () {
        builder.sources();

        assertEquals ("/sources", builder.getEscapedURI());
    }

    @Test
    public void source () {
        builder.source();

        assertEquals ("/source", builder.getEscapedURI());
    }

    @Test
    public void dates () {
        builder.dates();

        assertEquals ("/dates", builder.getEscapedURI());
    }

    @Test
    public void testSeriesId() {
        String seriesId = RANDOM_TEXT;

        builder.withSeriesId(seriesId);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.SERIES_ID, seriesId), actual);
    }

    @Test
    public void testReleaseId() {
        int releaseId = RANDOM_INT;

        builder.withReleaseId(releaseId);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.RELEASE_ID, releaseId), actual);
    }

    @Test
    public void testCategoryId() {
        int categoryId = RANDOM_INT;

        builder.withCategoryId(categoryId);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.CATEGORY_ID, categoryId), actual);
    }

    @Test
    public void testSourceId() {
        int sourceId = RANDOM_INT;

        builder.withSourceId(sourceId);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.SOURCE_ID, sourceId), actual);
    }

    @Test
    public void testRealtimeStartDate() {
        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date realtimeStart = calendar.getTime();

        builder.withRealtimeStart(realtimeStart);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.REALTIME_START, VALID_DATE), actual);
    }

    @Test
    public void testRealtimeStartString() {
        String realtimeStart = VALID_DATE;

        builder.withRealtimeStart(realtimeStart);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.REALTIME_START, VALID_DATE), actual);
    }

    @Test(expected=InvalidDateFormatException.class)
    public void testRealtimeStartInvalidString() {
        builder.withRealtimeStart(INVALID_DATE);
    }

    @Test
    public void testRealtimeEndDate() {
        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date realtimeEnd = calendar.getTime();

        builder.withRealtimeEnd(realtimeEnd);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.REALTIME_END, VALID_DATE), actual);
    }

    @Test
    public void testRealtimeEndString() {
        String realtimeEnd = VALID_DATE;

        builder.withRealtimeEnd(realtimeEnd);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.REALTIME_END, VALID_DATE), actual);
    }

    @Test(expected=InvalidDateFormatException.class)
    public void testRealtimeEndInvalidString() {
        builder.withRealtimeEnd(INVALID_DATE);
    }

    @Test
    public void testLimit() {
        int limit = RANDOM_INT;

        builder.withLimit(limit);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.LIMIT, limit), actual);
    }

    @Test(expected=LimitOutOfBoundsException.class)
    public void testLimitLessThan1() {
        int limit = 0;

        builder.withLimit(limit);
    }

    @Test(expected=LimitOutOfBoundsException.class)
    public void testLimitGreaterThan100000() {
        int limit = 100001;

        builder.withLimit(limit);
    }

    @Test
    public void testOffset() {

        int offset = 2;

        builder.withOffset(offset);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OFFSET, Integer.toString(offset)),
            actual);
    }

    @Test(expected=OffsetOutOfBoundsException.class)
    public void testInvalidOffset() {

        int offset = -1;

        builder.withOffset(offset);
    }

    @Test
    public void testOrderBy() {
        OrderBy orderBy = OrderBy.searchRank;

        builder.withOrderBy(orderBy);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.ORDER_BY, orderBy.toString()), actual);
    }

    @Test
    public void testSortOrder() {
        SortOrder sortOrder = SortOrder.desc;

        builder.withSortOrder(sortOrder);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.SORT_ORDER, sortOrder.toString()), actual);
    }

    @Test
    public void testFilterVariable() {
        FilterVariable filterVariable = FilterVariable.seasonalAdjustment;

        builder.withFilterVariable(filterVariable);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.FILTER_VARIABLE, filterVariable.toString()),
            actual);
    }

    @Test
    public void testFilterValue() {

        builder.withFilterValue(FilterValue.macro);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.FILTER_VALUE, FilterValue.macro.toString()),
            actual);
    }

    @Test
    public void testIncludeReleaseDatesWithNoData() {

        builder.withIncludeReleaseDatesWithNoData(false);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(
                QueryBuilder.INCLUDE_RELEASE_DATES_WITH_NO_DATA,
                Boolean.FALSE.toString()),
                actual);
    }

    @Test
    public void testObservationStartDate() {

        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date observationStart = calendar.getTime();

        builder.withObservationStart(observationStart);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OBSERVATION_START, VALID_DATE),
            actual);
    }

    @Test
    public void testObservationStartString() {

        String observationStart = VALID_DATE;

        builder.withObservationStart(observationStart);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OBSERVATION_START, observationStart),
            actual);
    }

    @Test(expected=InvalidDateFormatException.class)
    public void testObservationStartInvalidString() {
        builder.withObservationStart(INVALID_DATE);
    }

    @Test
    public void testObservationEndDate() {

        calendar.set(Calendar.YEAR, 1981);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date observationEnd = calendar.getTime();

        builder.withObservationEnd(observationEnd);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OBSERVATION_END, VALID_DATE),
            actual);
    }

    @Test
    public void testObservationEndString() {

        String observationEnd = VALID_DATE;

        builder.withObservationEnd(observationEnd);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OBSERVATION_END, observationEnd),
            actual);
    }

    @Test(expected=InvalidDateFormatException.class)
    public void testObservationEndInvalidString() {
        builder.withObservationEnd(INVALID_DATE);
    }

    @Test
    public void testUnits() {
        Unit unit = Unit.lin;

        builder.withUnits(unit);

        String actual = builder.getEscapedURI();

        assertEquals (format(QueryBuilder.UNITS, unit.toString()), actual);
    }

    @Test
    public void testFrequency() {
        Frequency frequency = Frequency.q;

        builder.withFrequency(frequency);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.FREQUENCY, frequency.toString()),
            actual);
    }

    @Test
    public void testAggregationMethod() {
        AggregationMethod aggregationMethod = AggregationMethod.eop;

        builder.withAggregationMethod(aggregationMethod);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(
                QueryBuilder.AGGREGATION_METHOD,
                aggregationMethod.toString()),
            actual);
    }

    @Test
    public void testOutputType() {
        OutputType outputType = OutputType.observationsInitialReleaseOnly;

        builder.withOutputType(outputType);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.OUTPUT_TYPE, outputType.toString()),
            actual);
    }

    @Test
    public void testFileType() {
        FileType fileType = FileType.xls;

        builder.withFileType(fileType);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(FILE_TYPE, fileType.toString()),
            actual);
    }

    @Test
    public void testVintageDates() {
        String vintageDates = RANDOM_TEXT;

        builder.withVintageDates(vintageDates);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.VINTAGE_DATES_PARAM, vintageDates), actual);
    }

    @Test
    public void testSearchText() {
        String searchText = RANDOM_TEXT;

        builder.withSearchText(searchText);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.SEARCH_TEXT, searchText), actual);
    }

    @Test
    public void testSearchType() {

        builder.withSearchType(SearchType.fullText);

        String actual = builder.getEscapedURI();

        assertEquals (
            format(QueryBuilder.SEARCH_TYPE, SearchType.fullText.toString()),
            actual);
    }

    @Test
    public void testToStringUsingNullDate () {

        String actual = QueryBuilder.convertDates(
            "testToString",
            ",",
            (String[]) null
        );

        assertNull (actual);
    }

    @Test
    public void testToStringUsingEmptyDatesArray () {

        String actual = QueryBuilder.convertDates(
            "testToString",
            ",",
            new String[] {}
        );

        assertNull (actual);
    }

    @Test
    public void testToStringUsingOneDate () {

        String foo = "2001-01-21";

        String actual = QueryBuilder.convertDates(
            "testToString",
            ",",
            foo
        );

        assertEquals ("2001-01-21", actual);
    }

    @Test
    public void testToStringUsingTwoDates () {

        String foo = "2001-01-21",
            bar = "2001-01-21";

        String actual = QueryBuilder.convertDates(
            "testToString",
            ",",
            foo,
            bar
        );

        assertEquals ("2001-01-21,2001-01-21", actual);
    }

    @Test
    public void testToStringUsingFourDates () {

        String foo = "2001-01-21",
            bar = "2001-01-21",
            baz = "2001-01-21",
            boo = "2001-01-21";

        String actual = QueryBuilder.convertDates(
            "testToString",
            ",",
            foo,
            bar,
            baz,
            boo
        );

        assertEquals ("2001-01-21,2001-01-21,2001-01-21,2001-01-21", actual);
    }

    @Test(expected=InvalidParameterValue.class)
    public void testCombineWithNullValues () {
        String[] values = null;
        QueryBuilder.combine(QueryBuilder.SEMICOLON, values);
    }

    @Test(expected=InvalidParameterValue.class)
    public void testCombineWithEmptyValues () {
        String[] values = {};
        QueryBuilder.combine(QueryBuilder.SEMICOLON, values);
    }

    @Test
    public void testCombineWithOneValue () {

        String foo = "foo";

        String[] values = {foo};

        String actual = QueryBuilder.combine(QueryBuilder.SEMICOLON, values);

        assertEquals(foo, actual);
    }

    @Test
    public void testCombineWithTwoValues () {

        String foo = "foo", bar = "bar";

        String[] values = {foo, bar};

        String actual = QueryBuilder.combine(QueryBuilder.SEMICOLON, values);

        assertEquals("foo;bar", actual);
    }

    @Test
    public void setTagGroupId () {
        builder.withTagGroupId(TagGroupId.gen);

        String uri = builder.getEscapedURI();

        assertEquals("/?tag_group_id=gen", uri);
    }

    @Test
    public void setSeriesSearchText () {
        String foo = "foo";

        builder.withSeriesSearchText(foo);

        String uri = builder.getEscapedURI();

        assertEquals("/?series_search_text=foo", uri);
    }
}
