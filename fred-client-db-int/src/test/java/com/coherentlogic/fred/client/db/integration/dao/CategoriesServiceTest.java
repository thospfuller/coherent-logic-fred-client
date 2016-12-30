package com.coherentlogic.fred.client.db.integration.dao;

import static com.coherentlogic.coherent.data.model.core.util.Utils.using;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.builders.QueryBuilder;
import com.coherentlogic.fred.client.core.domain.Categories;
import com.coherentlogic.fred.client.core.domain.Category;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link CategoriesDAO} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class CategoriesDAOTest {

    @Autowired
    private CategoriesDAO categoriesDAO = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Categories categories = null;

    @Before
    public void setUp() throws Exception {
        // NPE? Have you set the FRED_API_KEY in the system properties?
        QueryBuilder builder = queryBuilderFactory.getInstance();

        Date realtimeStart = using (2001, Calendar.JANUARY, 20);
        Date realtimeEnd = using (2004, Calendar.MAY, 17);

        categories = builder
            .series()
            .categories()
            .withSeriesId("EXJPUS")
            .withRealtimeStart(realtimeStart)
            .withRealtimeEnd(realtimeEnd)
            .doGet (Categories.class);
    }

    @After
    public void tearDown() throws Exception {
        categoriesDAO = null;
        categories = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Category> categoryList = categories.getCategoryList();

        Category firstCategory = categoryList.get(0);

        assertNull (firstCategory.getPrimaryKey());
        assertNotNull (categoryList);
        assertEquals (2, categoryList.size());

        categoriesDAO.persist(categories);

        Long uniqueId = categories.getPrimaryKey();

        assertNotNull (uniqueId);

        Categories persistedCategories = categoriesDAO.find(uniqueId);

        List<Category> persistedCategoryList =
            persistedCategories.getCategoryList();

        assertNotNull (persistedCategories);
        assertEquals (2, persistedCategoryList.size());

        persistedCategoryList.remove(0);

        categoriesDAO.merge(persistedCategories);

        Categories mergedPersistedCategories = categoriesDAO.find(uniqueId);

        persistedCategoryList = mergedPersistedCategories.getCategoryList();

        assertEquals (1, persistedCategoryList.size());

        categoriesDAO.remove(mergedPersistedCategories);

        Categories nullCategories = categoriesDAO.find(uniqueId);

        assertNull (nullCategories);
    }
}
