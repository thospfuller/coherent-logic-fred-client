package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.ObservationBoundSpecification.OBSERVATION_END_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.ObservationBoundSpecification.OBSERVATION_START_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Helper class for the domain package that contains logic for testing the
 * {@link RealtimeBoundSpecification} setter methods.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ObservationBoundSpecificationTestUtils {

    public static void testSetObservationStart(
        ObservationBoundSpecification observationBoundBean,
        Flag flag
    ) {
        SerializableBean serializableBean = (SerializableBean) observationBoundBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            OBSERVATION_START_PROPERTY,
            null,
            TEST_DATE
        );

        observationBoundBean.setObservationStart(TEST_DATE);

        assertTrue (flag.isValue());
    }

    public static void testSetObservationEnd(
        ObservationBoundSpecification observationBoundBean,
        Flag flag
    ) {
        SerializableBean serializableBean =
            (SerializableBean) observationBoundBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            OBSERVATION_END_PROPERTY,
            null,
            TEST_DATE
        );

        observationBoundBean.setObservationEnd(TEST_DATE);

        assertTrue (flag.isValue());
    }
}
