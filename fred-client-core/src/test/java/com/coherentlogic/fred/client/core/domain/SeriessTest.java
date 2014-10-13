package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.FILE_TYPE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.OUTPUT_TYPE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.SERIES_LIST_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.UNITS_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Seriess} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SeriessTest {

    private final Flag flag = new Flag ();

    private Seriess seriess = null;

    @Before
    public void setUp() throws Exception {
        seriess = new Seriess ();
    }

    @After
    public void tearDown() throws Exception {
        seriess = null;
        flag.setValue(false);
    }

    @Test
    public void testSetSeriesList() {

        final List<Series> seriesList = new ArrayList<Series> ();

        testSetterMethod(
            seriess,
            flag,
            SERIES_LIST_PROPERTY,
            null,
            seriesList,
            new Action<Seriess> () {
                @Override
                public void execute(Seriess data) {
                    data.setSeriesList(seriesList);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(seriess, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(seriess, flag);
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(seriess, flag);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(
            seriess, flag, OrderBy.seriesId);
    }

    @Test
    public void testSetLimit() {
        PaginationSpecificationTestUtils.testSetLimit(seriess, flag);
    }

    @Test
    public void testSetOffset() {
        PaginationSpecificationTestUtils.testSetOffset(seriess, flag);
    }

    @Test
    public void testSetFilterVariable() {
        FilterSpecificationTestUtils.testSetFilterVariable(seriess, flag);
    }

    @Test
    public void testSetFilterValue() {
        FilterSpecificationTestUtils.testSetFilterValue(seriess, flag);
    }

    @Test
    public void testSetUnits() {
        testSetterMethod(
            seriess,
            flag,
            UNITS_PROPERTY,
            null,
            Unit.chg,
            new Action<Seriess> () {
                @Override
                public void execute(Seriess data) {
                    data.setUnits(Unit.chg);
                }
            }
        );
    }

    @Test
    public void testSetOutputType() {

        final OutputType outputType =
            OutputType.observationsByVintageDateNewAndRevisedObservationsOnly;

        testSetterMethod(
            seriess,
            flag,
            OUTPUT_TYPE_PROPERTY,
            null,
            outputType,
            new Action<Seriess> () {
                @Override
                public void execute(Seriess data) {
                    data.setOutputType(outputType);
                }
            }
        );
    }

    @Test
    public void testSetFileType() {
        testSetterMethod(
            seriess,
            flag,
            FILE_TYPE_PROPERTY,
            null,
            FileType.txt,
            new Action<Seriess> () {
                @Override
                public void execute(Seriess data) {
                    // We only ever expect XML but for testing we'll use txt.
                    data.setFileType(FileType.txt);
                }
            }
        );
    }

    @Test
    public void testSetCount() {
        testSetterMethod(
            seriess,
            flag,
            COUNT_PROPERTY,
            0,
            TEST_INT,
            new Action<Seriess> () {
                @Override
                public void execute(Seriess data) {
                    data.setCount(TEST_INT);
                }
            }
        );
    }
}
