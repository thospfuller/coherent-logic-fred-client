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
import com.coherentlogic.fred.client.core.domain.ReleaseDate;
import com.coherentlogic.fred.client.core.domain.ReleaseDates;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link ReleaseDatesDAO} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class ReleaseDatesDAOTest {

    @Autowired
    private ReleaseDatesDAO releaseDatesDAO = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private ReleaseDates releaseDates = null;

    @Before
    public void setUp() throws Exception {

        QueryBuilder builder =
            queryBuilderFactory.getInstance();

        releaseDates =
            builder
                .releases()
                .dates()
                .withRealtimeStart("2012-06-18")
                .withRealtimeEnd("2012-06-18")
                .doGet(ReleaseDates.class);
    }

    @After
    public void tearDown() throws Exception {
        releaseDatesDAO = null;
        releaseDates = null;
    }

    @Test
    public void reviewCRUDOperations () {
        List<ReleaseDate> releaseDateList = releaseDates.getReleaseDateList();

        assertNull (releaseDates.getPrimaryKey());
        assertNotNull (releaseDateList);
        assertEquals (3, releaseDateList.size());

        releaseDatesDAO.persist(releaseDates);

        Long primaryKey = releaseDates.getPrimaryKey();

        assertNotNull (primaryKey);

        ReleaseDates persistedReleaseDates = releaseDatesDAO.find(primaryKey);

        List<ReleaseDate> persistedReleaseDateList =
            persistedReleaseDates.getReleaseDateList();

        assertNotNull(persistedReleaseDateList);
        assertEquals(3, persistedReleaseDateList.size());

        persistedReleaseDateList.remove(0);

        releaseDatesDAO.merge(persistedReleaseDates);

        ReleaseDates mergedPersistedReleaseDates =
            releaseDatesDAO.find(primaryKey);

        List<ReleaseDate> mergedPersistedReleaseDateList =
            mergedPersistedReleaseDates.getReleaseDateList();

        assertNotNull(mergedPersistedReleaseDateList);
        assertEquals(2, mergedPersistedReleaseDateList.size());

        releaseDatesDAO.remove(mergedPersistedReleaseDates);

        ReleaseDates nullReleaseDates = releaseDatesDAO.find(primaryKey);

        assertNull (nullReleaseDates);
    }
}
