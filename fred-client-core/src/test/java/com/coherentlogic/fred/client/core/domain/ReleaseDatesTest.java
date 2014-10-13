package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.ReleaseDates.RELEASE_DATE_LIST_PROPERTY;
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
 * Unit test for the {@link ReleaseDates} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ReleaseDatesTest {

    private final Flag flag = new Flag ();

    private ReleaseDates releaseDates = null;

    @Before
    public void setUp() throws Exception {
        releaseDates = new ReleaseDates();
    }

    @After
    public void tearDown() throws Exception {
        releaseDates = null;
        flag.setValue(false);
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(releaseDates, flag);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(releaseDates, flag);
    }

    @Test
    public void testSetLimit() {
        PaginationSpecificationTestUtils.testSetLimit(releaseDates, flag);
    }

    @Test
    public void testSetOffset() {
        PaginationSpecificationTestUtils.testSetOffset(releaseDates, flag);
    }

    @Test
    public void testSetReleaseDateList() {

        final List<ReleaseDate> releaseDateList = new ArrayList<ReleaseDate> ();

        testSetterMethod(
            releaseDates,
            flag,
            RELEASE_DATE_LIST_PROPERTY,
            null,
            releaseDateList,
            new Action<ReleaseDates> () {
                @Override
                public void execute(ReleaseDates data) {
                    data.setReleaseDateList(releaseDateList);
                }
            }
        );
    }

    @Test
    public void testSetCount() {
        testSetterMethod(
            releaseDates,
            flag,
            COUNT_PROPERTY,
            0,
            TEST_INT,
            new Action<ReleaseDates> () {
                @Override
                public void execute(ReleaseDates data) {
                    data.setCount(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.
            testSetRealtimeStart(releaseDates, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.
            testSetRealtimeEnd(releaseDates, flag);
    }
}
