package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.FREQUENCY_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.FREQUENCY_SHORT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.LAST_UPDATED_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.NOTES_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.POPULARITY_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.SEASONAL_ADJUSTMENT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.SEASONAL_ADJUSTMENT_SHORT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.TITLE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.UNITS_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Series} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriesTest {

    private final Flag flag = new Flag ();

    private Series series = null;

    @Before
    public void setUp() throws Exception {
        series = new Series ();
    }

    @After
    public void tearDown() throws Exception {
        series = null;
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(series, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(series, flag);
    }

    @Test
    public void testSetTitle() {
        testSetterMethod(
            series,
            flag,
            TITLE_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setTitle(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetObservationStart() {
        ObservationBoundSpecificationTestUtils.
            testSetObservationStart(series, flag);
    }

    @Test
    public void testSetObservationEnd() {
        ObservationBoundSpecificationTestUtils.
            testSetObservationEnd(series, flag);
    }

    @Test
    public void testSetFrequency() {
        testSetterMethod(
            series,
            flag,
            FREQUENCY_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setFrequency(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetFrequencyShort() {
        testSetterMethod(
            series,
            flag,
            FREQUENCY_SHORT_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setFrequencyShort(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetUnits() {
        testSetterMethod(
            series,
            flag,
            UNITS_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setUnits(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetUnitsShort() {
        testSetterMethod(
            series,
            flag,
            UNITS_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setUnits(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetSeasonalAdjustment() {
        testSetterMethod(
            series,
            flag,
            SEASONAL_ADJUSTMENT_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setSeasonalAdjustment(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetSeasonalAdjustmentShort() {
        testSetterMethod(
            series,
            flag,
            SEASONAL_ADJUSTMENT_SHORT_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setSeasonalAdjustmentShort(
                        TEST_STRING
                    );
                }
            }
        );
    }

    @Test
    public void testSetLastUpdated() {
        testSetterMethod(
            series,
            flag,
            LAST_UPDATED_PROPERTY,
            null,
            TEST_DATE,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setLastUpdated(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetPopularity() {
        testSetterMethod(
            series,
            flag,
            POPULARITY_PROPERTY,
            null,
            TEST_INT,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setPopularity(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetNotes() {
        testSetterMethod(
            series,
            flag,
            NOTES_PROPERTY,
            null,
            TEST_STRING,
            new Action<Series> () {
                @Override
                public void execute(Series data) {
                    data.setNotes(TEST_STRING);
                }
            }
        );
    }
}
