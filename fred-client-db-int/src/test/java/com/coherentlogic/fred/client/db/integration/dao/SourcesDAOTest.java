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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.coherentlogic.fred.client.core.builders.QueryBuilder;
import com.coherentlogic.fred.client.core.domain.Source;
import com.coherentlogic.fred.client.core.domain.Sources;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link SourcesDAO} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class SourcesDAOTest {

    @Autowired
    private SourcesDAO sourcesDAO = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Sources sources = null;

    @Before
    public void setUp() throws Exception {

        QueryBuilder builder = queryBuilderFactory.getInstance();

        sources = builder
            .release()
            .sources()
            .setReleaseId(51L)
            .setRealtimeStart("2010-06-01")
            .setRealtimeEnd("2012-06-18")
            .doGet(Sources.class);
    }

    @After
    public void tearDown() throws Exception {
        sourcesDAO = null;
        sources = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Source> sourcesList = sources.getSourceList();

        assertNull (sources.getPrimaryKey());
        assertNotNull (sourcesList);
        assertEquals (2, sourcesList.size());

        sourcesDAO.persist(sources);

        Long primaryKey = sources.getPrimaryKey();

        assertNotNull (primaryKey);

        Sources persistedSources = sourcesDAO.find(primaryKey);

        List<Source> persistedSourceList = persistedSources.getSourceList();

        assertNotNull(persistedSourceList);
        assertEquals(2, persistedSourceList.size());

        persistedSourceList.remove(0);

        sourcesDAO.merge(persistedSources);

        Sources mergedPersistedSources = sourcesDAO.find(primaryKey);

        List<Source> mergedPersistedSourceList =
            mergedPersistedSources.getSourceList();

        assertNotNull(mergedPersistedSourceList);
        assertEquals(1, mergedPersistedSourceList.size());

        sourcesDAO.remove(mergedPersistedSources);

        Sources nullSources = sourcesDAO.find(primaryKey);

        assertNull (nullSources);
    }
}
