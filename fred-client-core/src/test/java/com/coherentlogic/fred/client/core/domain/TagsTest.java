package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.COUNT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.Tags.TAG_LIST_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
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
 * Unit test for the {@link Tags} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class TagsTest {

    private final Flag flag = new Flag ();

    private Tags tags = null;

    @Before
    public void setUp() throws Exception {
        tags = new Tags ();
    }

    @After
    public void tearDown() throws Exception {
        tags = null;
        flag.setValue(false);
    }

    @Test
    public void testSetCount() {
        TestUtils.testSetCount (
            tags,
            flag,
            COUNT_PROPERTY,
            TEST_INT,
            new Action<Tags> () {
                @Override
                public void execute(Tags tags) {
                    tags.setCount(TEST_INT);
                }
            }
        );
    }

    @Test
    public void testSetTagList() {
        final List<Tag> tagList =
            new ArrayList<Tag> ();

        createAndAssignPropertyChangeListener(
            tags,
            flag,
            TAG_LIST_PROPERTY,
            null,
            tagList
        );

        tags.setTagList(tagList);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(tags, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(tags, flag);
    }

    @Test
    public void testSetSortOrder() {
        SortOrderSpecificationTestUtils.testSetSortOrder(tags, flag);
    }

    @Test
    public void testSetOrderBy() {
        OrderBySpecificationTestUtils.testSetOrderBy(
            tags,
            flag,
            OrderBy.seriesId
        );
    }

    @Test
    public void testSetLimit() {
        PaginationSpecificationTestUtils.testSetLimit(tags, flag);
    }

    @Test
    public void testSetOffset() {
        PaginationSpecificationTestUtils.testSetOffset (tags, flag);
    }
}
