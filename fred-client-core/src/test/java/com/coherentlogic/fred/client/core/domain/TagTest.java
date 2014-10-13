package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_LONG;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Tag} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class TagTest {

    private final Flag flag = new Flag ();

    private Tag tag = null;

    @Before
    public void setUp() throws Exception {
        tag = new Tag ();
    }

    @After
    public void tearDown() throws Exception {
        tag = null;
        flag.setValue(false);
    }

    @Test
    public void testSetName () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.NAME_PROPERTY,
            null,
            TEST_STRING
        );

        tag.setName(TEST_STRING);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetGroupId () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.GROUP_ID_PROPERTY,
            null,
            TEST_STRING
        );

        tag.setGroupId (TEST_STRING);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetNotes () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.NOTES_PROPERTY,
            null,
            TEST_STRING
        );

        tag.setNotes (TEST_STRING);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetCreated () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.CREATED_PROPERTY,
            null,
            TEST_STRING
        );

        tag.setCreated (TEST_STRING);

        assertTrue (flag.isValue());
    }

    @Test
    public void testPopularity () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.POPULARITY_PROPERTY,
            null,
            TEST_LONG
        );

        tag.setPopularity(TEST_LONG);

        assertTrue (flag.isValue());
    }

    @Test
    public void testSetSeriesCount () {
        createAndAssignPropertyChangeListener(
            tag,
            flag,
            PropertyNames.SERIES_COUNT_PROPERTY,
            null,
            TEST_LONG
        );

        tag.setSeriesCount(TEST_LONG);

        assertTrue (flag.isValue());
    }
}
