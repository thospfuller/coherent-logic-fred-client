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
import com.coherentlogic.fred.client.core.domain.Tag;
import com.coherentlogic.fred.client.core.domain.Tags;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;
import com.coherentlogic.fred.client.db.integration.services.TagsService;

/**
 * Unit test for the {@link TagsRepository} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class TagsDAOTest {

    @Autowired
    private TagsService tagsService = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Tags tags = null;

    @Before
    public void setUp() throws Exception {

        Date realtimeStart = using (2001, Calendar.JANUARY, 20);
        Date realtimeEnd = using (2004, Calendar.MAY, 17);

        QueryBuilder builder = queryBuilderFactory.
            getInstance();

        tags = builder
            .series()
            .search()
            .tags()
            .withRealtimeStart(realtimeStart)
            .withRealtimeEnd(realtimeEnd)
            .withSeriesSearchText("monetary service index")
            .doGet(Tags.class);
    }

    @After
    public void tearDown() throws Exception {
        tagsService = null;
        tags = null;
    }

    @Test
    public void reviewCRUDOperations () {

        List<Tag> tagsList = tags.getTagList();

        assertNull (tags.getPrimaryKey());
        assertNotNull (tagsList);
        assertEquals (12, tagsList.size());

        tagsService.save(tags);

        Long primaryKey = tags.getPrimaryKey();

        assertNotNull(primaryKey);

        Tags persistedTags = tagsService.findOne(primaryKey);

        List<Tag> persistedTagList = persistedTags.
            getTagList();

        assertNotNull(persistedTagList);
        assertEquals(12, persistedTagList.size());

        persistedTagList.remove(0);

        tagsService.save(persistedTags);

        Tags mergedPersistedTags =
            tagsService.findOne(primaryKey);

        List<Tag> mergedPersistedTagList =
            mergedPersistedTags.getTagList();

        assertNotNull(mergedPersistedTagList);
        assertEquals(11, mergedPersistedTagList.size());

        tagsService.delete(mergedPersistedTags);

        Tags nullVintageDates = tagsService.findOne(primaryKey);

        assertNull (nullVintageDates);
    }
}
