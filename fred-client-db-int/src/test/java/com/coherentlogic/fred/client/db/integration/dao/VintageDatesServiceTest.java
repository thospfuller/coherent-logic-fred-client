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
import com.coherentlogic.fred.client.core.domain.SortOrder;
import com.coherentlogic.fred.client.core.domain.VintageDate;
import com.coherentlogic.fred.client.core.domain.VintageDates;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link ReleasesRepository} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class VintageDatesServiceTest {

    @Autowired
    private VintageDatesRepository vintageDatesService = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private VintageDates vintageDates = null;

    @Before
    public void setUp() throws Exception {

        Date realtimeStart = using (2001, Calendar.JANUARY, 20);
        Date realtimeEnd = using (2004, Calendar.MAY, 17);

        QueryBuilder builder = queryBuilderFactory.
            getInstance();

        vintageDates = builder
            .series()
            .vintageDates()
            .withSeriesId("GNPCA")
            .withRealtimeStart(realtimeStart)
            .withRealtimeEnd(realtimeEnd)
            .withLimit(100)
            .withOffset(1)
            .withSortOrder(SortOrder.desc)
            .doGet(VintageDates.class);
    }

    @After
    public void tearDown() throws Exception {
        vintageDatesService = null;
        vintageDates = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<VintageDate> vintangeDateList = vintageDates.getVintageDateList();

        assertNull (vintageDates.getPrimaryKey());
        assertNotNull (vintangeDateList);
        assertEquals (7, vintangeDateList.size());

        vintageDatesService.save(vintageDates);

        Long primaryKey = vintageDates.getPrimaryKey();

        assertNotNull(primaryKey);

        VintageDates persistedVintageDates = vintageDatesService.findOne(primaryKey);

        List<VintageDate> persistedVintageDateList = persistedVintageDates.
            getVintageDateList();

        assertNotNull(persistedVintageDateList);
        assertEquals(7, persistedVintageDateList.size());

        persistedVintageDateList.remove(0);

        vintageDatesService.save(persistedVintageDates);

        VintageDates mergedPersistedVintageDates =
            vintageDatesService.findOne(primaryKey);

        List<VintageDate> mergedPersistedVintageDateList =
            mergedPersistedVintageDates.getVintageDateList();

        assertNotNull(mergedPersistedVintageDateList);
        assertEquals(6, mergedPersistedVintageDateList.size());

        vintageDatesService.delete(mergedPersistedVintageDates);

        VintageDates nullVintageDates = vintageDatesService.findOne(primaryKey);

        assertNull (nullVintageDates);
    }
}
