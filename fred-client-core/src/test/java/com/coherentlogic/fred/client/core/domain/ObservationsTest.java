package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.ObservationBoundSpecification.OBSERVATION_END_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.ObservationBoundSpecification.OBSERVATION_START_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.Observations.MESSAGE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.Observations.OBSERVATION_LIST_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.OrderBySpecification.ORDER_BY_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.LIMIT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.OFFSET_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.FILE_TYPE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.OUTPUT_TYPE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.UNITS_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_END_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_START_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.SortOrderSpecification.SORT_ORDER_PROPERTY;
import static com.coherentlogic.fred.client.core.util.Constants.DEFAULT_LIMIT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_LONG;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Observations} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ObservationsTest {

    private final Flag flag = new Flag ();

    private Observations observations = null;

    @Before
    public void setUp() throws Exception {
        observations = new Observations ();
    }

    @After
    public void tearDown() throws Exception {
        observations = null;
    }

    @Test
    public void testSetObservationList() {

        final List<Observation> observationList = new ArrayList<Observation> ();

        testSetterMethod(
            observations,
            flag,
            OBSERVATION_LIST_PROPERTY,
            null,
            observationList,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setObservationList(observationList);
                }
            }
        );
    }

    @Test
    public void testSetSortOrder() {
        testSetterMethod(
            observations,
            flag,
            SORT_ORDER_PROPERTY,
            SortOrder.asc,
            SortOrder.desc,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setSortOrder(SortOrder.desc);
                }
            }
        );
    }

    @Test
    public void testSetOrderBy() {
        testSetterMethod(
            observations,
            flag,
            ORDER_BY_PROPERTY,
            OrderBy.seriesId,
            OrderBy.popularity,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setOrderBy(OrderBy.popularity);
                }
            }
        );
    }

    @Test
    public void testSetLimit() {
        testSetterMethod(
            observations,
            flag,
            LIMIT_PROPERTY,
            (long) DEFAULT_LIMIT,
            TEST_LONG,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setLimit(TEST_LONG);
                }
            }
        );
    }

    @Test
    public void testSetOffset() {
        testSetterMethod(
            observations,
            flag,
            OFFSET_PROPERTY,
            0,
            TEST_INT,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setOffset(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetObservationStart() {
        testSetterMethod(
            observations,
            flag,
            OBSERVATION_START_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.
                        setObservationStart(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetObservationEnd() {
        testSetterMethod(
            observations,
            flag,
            OBSERVATION_END_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setObservationEnd(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeStart() {
        testSetterMethod(
            observations,
            flag,
            REALTIME_START_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setRealtimeStart(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeEnd() {
        testSetterMethod(
            observations,
            flag,
            REALTIME_END_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setRealtimeEnd(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetUnits() {
        testSetterMethod(
            observations,
            flag,
            UNITS_PROPERTY,
            null,
            Unit.ch1,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setUnits(Unit.ch1);
                }
            }
        );
    }

    @Test
    public void testSetOutputType() {
        testSetterMethod(
            observations,
            flag,
            OUTPUT_TYPE_PROPERTY,
            null,
            OutputType.observationsByRealTimePeriod,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.
                        setOutputType(OutputType.observationsByRealTimePeriod);
                }
            }
        );
    }

    @Test
    public void testSetFileType() {
        testSetterMethod(
            observations,
            flag,
            FILE_TYPE_PROPERTY,
            null,
            FileType.xls,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setFileType(FileType.xls);
                }
            }
        );
    }

    @Test
    public void testSetCount() {
        testSetterMethod(
            observations,
            flag,
            COUNT_PROPERTY,
            0,
            TEST_INT,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setCount(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetMessage() {

        final Message message = new Message (null);

        testSetterMethod(
            observations,
            flag,
            MESSAGE_PROPERTY,
            null,
            message,
            new Action<Observations> () {
                @Override
                public void execute(Observations data) {
                    observations.setMessage(message);
                }
            }
        );
    }
}
