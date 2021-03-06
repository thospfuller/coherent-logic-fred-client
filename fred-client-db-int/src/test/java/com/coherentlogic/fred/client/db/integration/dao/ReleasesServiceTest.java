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
import com.coherentlogic.fred.client.db.integration.services.ReleasesService;

/**
 * Unit test for the {@link ReleasesRepository} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class ReleasesServiceTest {

    @Autowired
    private ReleasesService releasesService = null;

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
                .withSeriesId("IRA")
                .withRealtimeStart(realtimeStart)
                .withRealtimeEnd(realtimeEnd)
                .doGet(Releases.class);
    }

    @After
    public void tearDown() throws Exception {
        releasesService = null;
        releases = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Release> releaseList = releases.getReleaseList();

        assertNull (releases.getPrimaryKey());
        assertNotNull (releaseList);
        assertEquals (2, releaseList.size());

        releasesService.save(releases);

        Long primaryKey = releases.getPrimaryKey();

        assertNotNull (primaryKey);

        Releases persistedReleases = releasesService.findOne(primaryKey);

        List<Release> persistedReleaseList = persistedReleases.getReleaseList();

        assertNotNull(persistedReleaseList);
        assertEquals(2, persistedReleaseList.size());

        persistedReleaseList.remove(0);

        releasesService.save(persistedReleases);

        Releases mergedPersistedReleases = releasesService.findOne(primaryKey);

        List<Release> mergedPersistedReleaseList =
            mergedPersistedReleases.getReleaseList();

        assertNotNull(mergedPersistedReleaseList);
        assertEquals(1, mergedPersistedReleaseList.size());

        releasesService.delete(mergedPersistedReleases);

        Releases nullReleases = releasesService.findOne(primaryKey);

        assertNull (nullReleases);
    }
}
