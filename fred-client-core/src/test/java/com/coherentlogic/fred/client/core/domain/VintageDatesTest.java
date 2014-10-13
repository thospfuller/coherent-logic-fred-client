package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.LIMIT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.OFFSET_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.VintageDates.VINTAGE_DATE_LIST;
import static com.coherentlogic.fred.client.core.util.Constants.DEFAULT_LIMIT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_LONG;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;
import com.coherentlogic.fred.client.core.util.TestUtils;

/**
 * Unit test for the {@link VintageDates} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class VintageDatesTest {

    private VintageDates vintageDates = null;

    private final Flag flag = new Flag ();

    @Before
    public void setUp() throws Exception {
        vintageDates = new VintageDates ();
    }

    @After
    public void tearDown() throws Exception {
        vintageDates = null;
        flag.setValue(false);
    }

    @Test
    public void testSetVintageDateList() {

        final List<VintageDate> vintageDateList =
            new ArrayList<VintageDate> ();

        createAndAssignPropertyChangeListener(
            vintageDates,
            flag,
            VINTAGE_DATE_LIST,
            null,
            vintageDateList
        );

        vintageDates.setVintageDateList(vintageDateList);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(vintageDates, flag);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(vintageDates, flag);
    }

    @Test
    public void testSetLimit() {
        createAndAssignPropertyChangeListener(
            vintageDates,
            flag,
            LIMIT_PROPERTY,
            (long) DEFAULT_LIMIT,
            TEST_LONG
        );

        vintageDates.setLimit(TEST_LONG);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetOffset() {
        createAndAssignPropertyChangeListener(
            vintageDates,
            flag,
            OFFSET_PROPERTY,
            0,
            TEST_INT
        );

        vintageDates.setOffset(TEST_INT);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.
            testSetRealtimeStart(vintageDates, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.
            testSetRealtimeEnd(vintageDates, flag);
    }

    @Test
    public void testSetCount() {
        TestUtils.testSetCount (
            vintageDates,
            flag,
            COUNT_PROPERTY,
            TEST_INT,
            new Action<VintageDates> () {
                @Override
                public void execute(VintageDates vintageDates) {
                    vintageDates.setCount(TEST_INT);
                }
            }
        );
    }
}
