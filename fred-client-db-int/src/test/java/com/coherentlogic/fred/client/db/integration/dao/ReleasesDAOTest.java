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
import com.coherentlogic.fred.client.core.domain.Release;
import com.coherentlogic.fred.client.core.domain.Releases;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link ReleasesDAO} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class ReleasesDAOTest {

    @Autowired
    private ReleasesDAO releasesDAO = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Releases releases = null;

    @Before
    public void setUp() throws Exception {

        QueryBuilder builder = queryBuilderFactory.getInstance();

        Date realtimeStart = using (2001, Calendar.JANUARY, 20);
        Date realtimeEnd = using (2004, Calendar.MAY, 17);

        releases =
            builder
                .series()
                .release()
                .setSeriesId("IRA")
                .setRealtimeStart(realtimeStart)
                .setRealtimeEnd(realtimeEnd)
                .doGet(Releases.class);
    }

    @After
    public void tearDown() throws Exception {
        releasesDAO = null;
        releases = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Release> releaseList = releases.getReleaseList();

        assertNull (releases.getPrimaryKey());
        assertNotNull (releaseList);
        assertEquals (2, releaseList.size());

        releasesDAO.persist(releases);

        Long primaryKey = releases.getPrimaryKey();

        assertNotNull (primaryKey);

        Releases persistedReleases = releasesDAO.find(primaryKey);

        List<Release> persistedReleaseList = persistedReleases.getReleaseList();

        assertNotNull(persistedReleaseList);
        assertEquals(2, persistedReleaseList.size());

        persistedReleaseList.remove(0);

        releasesDAO.merge(persistedReleases);

        Releases mergedPersistedReleases = releasesDAO.find(primaryKey);

        List<Release> mergedPersistedReleaseList =
            mergedPersistedReleases.getReleaseList();

        assertNotNull(mergedPersistedReleaseList);
        assertEquals(1, mergedPersistedReleaseList.size());

        releasesDAO.remove(mergedPersistedReleases);

        Releases nullReleases = releasesDAO.find(primaryKey);

        assertNull (nullReleases);
    }
}
