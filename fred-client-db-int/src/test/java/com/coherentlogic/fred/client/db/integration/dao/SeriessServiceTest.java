package com.coherentlogic.fred.client.db.integration.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.coherentlogic.fred.client.core.domain.Series;
import com.coherentlogic.fred.client.core.domain.Seriess;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;
import com.coherentlogic.fred.client.db.integration.services.SeriessService;

/**
 * This tests saving an instance of {@link Seriess} to the H2 database, which is
 * running in-memory. Note that the seriess has 24 instances of {@link Series}
 * which are saved as well.
 *
 * This test is not supposed to cover every possible scenario for persisting
 * data to and from the database -- instead the goal is to verify that the JPA
 * annotations have been applied properly.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class SeriessServiceTest {

    @Autowired
    private SeriessService seriessService = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Seriess seriess = null;

    @Before
    public void setUp() throws Exception {

        QueryBuilder builder = queryBuilderFactory.getInstance();

        long categoryId = 125;

        seriess = builder
            .category()
            .series()
            .withCategoryId(categoryId)
            .doGet(Seriess.class);
    }

    @After
    public void tearDown() throws Exception {
        seriessService = null;
        seriess = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Series> seriesList = seriess.getSeriesList();

        assertNull (seriess.getPrimaryKey());
        assertNotNull (seriesList);
        assertEquals (45, seriesList.size());

        seriessService.save(seriess);

        Long uniqueId = seriess.getPrimaryKey();

        assertNotNull (uniqueId);

        Seriess persistedSeriess = seriessService.findOne(uniqueId);
        List<Series> persistedSeriesList = seriess.getSeriesList();

        assertNotNull (persistedSeriess);
        assertEquals (45, persistedSeriesList.size());

        persistedSeriesList.remove(0);

        seriessService.save(persistedSeriess);

        Seriess mergedPersistedSeriess = seriessService.findOne(uniqueId);

        persistedSeriesList = mergedPersistedSeriess.getSeriesList();

        assertEquals (44, persistedSeriesList.size());

        seriessService.delete(mergedPersistedSeriess);

        Seriess nullSeriess = seriessService.findOne(uniqueId);

        assertNull(nullSeriess);
    }
}
