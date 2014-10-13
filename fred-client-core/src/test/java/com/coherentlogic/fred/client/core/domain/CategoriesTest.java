package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.Categories.CATEGORY_LIST_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Categories} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class CategoriesTest {

    private Categories categories = null;

    private final Flag flag = new Flag ();

    @Before
    public void setUp() throws Exception {
        categories = new Categories();
    }

    @After
    public void tearDown() throws Exception {
        categories = null;
        flag.setValue(false);
    }

    @Test
    public void testSetCategoryList() {

        final List<Category> categoryList = new ArrayList<Category> ();

        testSetterMethod(
            categories,
            flag,
            CATEGORY_LIST_PROPERTY,
            null,
            categoryList,
            new Action<Categories> () {
                @Override
                public void execute(Categories data) {
                    categories.setCategoryList(categoryList);
                }
            }
        );
    }
}
