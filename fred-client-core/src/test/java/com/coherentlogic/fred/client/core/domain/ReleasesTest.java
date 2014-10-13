package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.Releases.RELEASE_LIST_PROPERTY;
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
 * Unit test for the {@link Releases} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ReleasesTest {

    private final Flag flag = new Flag ();

    private Releases releases = null;

    @Before
    public void setUp() throws Exception {
        releases = new Releases ();
    }

    @After
    public void tearDown() throws Exception {
        releases = null;
        flag.setValue(false);
    }

    @Test
    public void testSetReleaseList() {

        final List<Release> releaseList = new ArrayList<Release> ();

        testSetterMethod(
            releases, 
            flag,
            RELEASE_LIST_PROPERTY,
            null,
            releaseList,
            new Action<Releases> () {
                @Override
                public void execute(Releases data) {
                    data.setReleaseList(releaseList);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.
            testSetRealtimeStart(releases, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(releases, flag);
    }

    @Test
    public void testSetLimit() {
        PaginationSpecificationTestUtils.testSetLimit(releases, flag, 0L);
    }

    @Test
    public void testSetOffset() {
        PaginationSpecificationTestUtils.testSetOffset(releases, flag);
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(releases, flag, null);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(releases, flag);
    }

    @Test
    public void testSetCount() {
        testSetterMethod(
            releases,
            flag,
            COUNT_PROPERTY,
            0,
            TEST_INT,
            new Action<Releases> () {
                @Override
                public void execute(Releases data) {
                    data.setCount(TEST_INT);
                }
            }
        );
    }
}
