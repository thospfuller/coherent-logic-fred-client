package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.Category.PARENT_ID_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.NAME_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Category} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class CategoryTest {

    private final Flag flag = new Flag ();

    private Category category = null;

    @Before
    public void setUp() throws Exception {
        category = new Category ();
    }

    @After
    public void tearDown() throws Exception {
        category = null;
    }

    @Test
    public void testSetName() {
        testSetterMethod(
            category,
            flag,
            NAME_PROPERTY,
            null,
            TEST_STRING,
            new Action<Category> () {
                @Override
                public void execute(Category data) {
                    category.setName(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetParentId() {
        testSetterMethod(
            category,
            flag,
            PARENT_ID_PROPERTY,
            null,
            TEST_STRING,
            new Action<Category> () {
                @Override
                public void execute(Category data) {
                    category.setParentId(TEST_STRING);
                }
            }
        );
    }
}
