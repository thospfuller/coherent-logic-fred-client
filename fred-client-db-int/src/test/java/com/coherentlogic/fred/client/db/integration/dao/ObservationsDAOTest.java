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
import com.coherentlogic.fred.client.core.domain.AggregationMethod;
import com.coherentlogic.fred.client.core.domain.FileType;
import com.coherentlogic.fred.client.core.domain.Frequency;
import com.coherentlogic.fred.client.core.domain.Message;
import com.coherentlogic.fred.client.core.domain.Observation;
import com.coherentlogic.fred.client.core.domain.Observations;
import com.coherentlogic.fred.client.core.domain.OutputType;
import com.coherentlogic.fred.client.core.domain.SortOrder;
import com.coherentlogic.fred.client.core.domain.Unit;
import com.coherentlogic.fred.client.core.factories.QueryBuilderFactory;

/**
 * Unit test for the {@link ObservationsDAO} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations={"/spring/application-context.xml"})
public class ObservationsDAOTest {

    @Autowired
    private ObservationsDAO observationsDAO = null;

    @Autowired
    private QueryBuilderFactory queryBuilderFactory = null;

    private Observations observations = null;

    @Before
    public void setUp() throws Exception {
        QueryBuilder builder = queryBuilderFactory.
            getInstance();

        observations =
            builder
                .series()
                .observations()
                .withSeriesId("GNPCA")
                .doGet(Observations.class);
    }

    @After
    public void tearDown() throws Exception {
        observations = null;
    }

    @Test
    public void reviewCRUDOperations () {

        observations.getMessage();

        List<Observation> observationList = observations.getObservationList();

        assertNotNull (observations);
        assertNotNull (observationList);
        assertEquals (87, observationList.size());
        assertNull (observations.getPrimaryKey());

        observationsDAO.persist(observations);

        Long uniqueId = observations.getPrimaryKey();

        assertNotNull (uniqueId);

        Observations persistedObservations = observationsDAO.find(uniqueId);

        List<Observation> persistedObservationList =
            persistedObservations.getObservationList();

        assertEquals (87, persistedObservationList.size());

        persistedObservationList.remove(0);

        observationsDAO.merge(persistedObservations);

        Observations mergedPersistedObservations =
            observationsDAO.find(uniqueId);

        persistedObservationList = mergedPersistedObservations.
            getObservationList();

        assertEquals (86, persistedObservationList.size());

        observationsDAO.remove(mergedPersistedObservations);

        Observations nullObservations = observationsDAO.find(uniqueId);

        assertNull (nullObservations);
    }

    @Test
    public void reviewCRUDOperationsWhereContentNotNull () {

        Date realtimeStart = using (2001, Calendar.JANUARY, 20);
        Date realtimeEnd = using (2004, Calendar.MAY, 17);

        Date observationStart = realtimeStart;
        Date observationEnd = realtimeEnd;

        QueryBuilder builder = queryBuilderFactory.
            getInstance();

        observations =
            builder
                .series()
                .observations()
                .withSeriesId("GNPCA")
                .withRealtimeStart(realtimeStart)
                .withRealtimeEnd(realtimeEnd)
                .withLimit(10)
                .withOffset(5)
                .withSortOrder(SortOrder.desc)
                .withObservationStart(observationStart)
                .withObservationEnd(observationEnd)
                .withUnits(Unit.lin)
                .withFrequency(Frequency.a)
                .withAggregationMethod(AggregationMethod.sum)
                .withOutputType(OutputType.observationsByRealTimePeriod)
                .withFileType(FileType.xls)
                .doGet(Observations.class);

            Message content = observations.getMessage();

            assertNotNull (content);

            observationsDAO.merge(observations);

            Long id = observations.getPrimaryKey();

            // The content is in the message, not in the domain properties, so
            // everything in this case will be null and hence we do not expect
            // to save this to the database.
            assertNull (id);
    }
}
