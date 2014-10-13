package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.SOURCE_LIST_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;
import com.coherentlogic.fred.client.core.util.TestUtils;

/**
 * Unit test for the {@link Sources} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SourcesTest {

    private final Flag flag = new Flag ();

    private Sources sources = null;

    @Before
    public void setUp() throws Exception {
        sources = new Sources ();
    }

    @After
    public void tearDown() throws Exception {
        sources = null;
        flag.setValue(false);
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(sources, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(sources, flag);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(
            sources, flag, OrderBy.seriesId);
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(sources, flag);
    }

    @Test
    public void testSetCount() {
        TestUtils.testSetCount (
            sources,
            flag,
            COUNT_PROPERTY,
            TEST_INT,
            new Action<Sources> () {
                @Override
                public void execute(Sources sources) {
                    sources.setCount(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetOffset() {
        PaginationSpecificationTestUtils.testSetOffset (sources, flag);
    }

    @Test
    public void testSetLimit() {
        PaginationSpecificationTestUtils.testSetLimit(sources, flag);
    }

    @Test
    public void testSetSourceList() {

        final List<Source> sourceList = new ArrayList<Source> ();

        testSetterMethod(
            sources,
            flag,
            SOURCE_LIST_PROPERTY,
            null,
            sourceList,
            new Action<Sources> () {
                @Override
                public void execute(Sources sources) {
                    sources.setSourceList(sourceList);
                }
            }
        );
    }
}
