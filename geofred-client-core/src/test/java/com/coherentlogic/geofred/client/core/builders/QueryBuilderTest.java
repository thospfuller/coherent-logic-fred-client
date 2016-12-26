package com.coherentlogic.geofred.client.core.builders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.coherentlogic.geofred.client.core.domain.SeriesData;
import com.coherentlogic.geofred.client.core.domain.SeriesGroups;
import com.coherentlogic.geofred.client.core.domain.Shapes;

public class QueryBuilderTest {

    static final String FRED_API_KEY = "FRED_API_KEY",
        GEOFRED_REST_TEMPLATE_ID = "geoFREDRestTemplate";

    /**
     * This value should be set in the environment properties of the operating
     * system. Make sure to restart your IDE and/or OS shell once this has been
     * set.
     */
    static final String API_KEY = System.getenv(FRED_API_KEY);

    private final ApplicationContext context
        = new FileSystemXmlApplicationContext ("src/test/resources/spring/application-context.xml");

    private RestTemplate restTemplate = null;

    private QueryBuilder builder = null;

    @Before
    public void setUp () throws Exception {

        restTemplate = (RestTemplate) context.getBean (GEOFRED_REST_TEMPLATE_ID);

        builder = new QueryBuilder (restTemplate);
    }

    @After
    public void tearDown () throws Exception {
        restTemplate = null;
        builder = null;
    }

    @Test
    public void testGetShapes() {

        Shapes shapes = builder.shapes().withApiKey(API_KEY).withShapeTypeAsBEA().doGetAsShapes();

        System.out.println("shapes: " + shapes);
    }

    @Test
    public void testGetSeriesGroups() {

        SeriesGroups seriesGroups = builder
            .seriesGroup()
            .withApiKey(API_KEY)
            .withSeriesId("SMU56000000500000001a")
            .doGetAsSeriesGroups();

        System.out.println("seriesGroups: " + seriesGroups);
    }

    @Test
    public void testGetSeriesData() {

        SeriesData seriesData = builder
            .seriesData()
            .withApiKey(API_KEY)
            .withSeriesId("WIPCPI")
            .withStartDate("2012-01-01")
            .doGetAsSeriesData();

        System.out.println("seriesData: " + seriesData);
    }
}
