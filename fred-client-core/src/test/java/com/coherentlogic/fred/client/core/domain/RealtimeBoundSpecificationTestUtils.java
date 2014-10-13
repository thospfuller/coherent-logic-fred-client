package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_END_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_START_PROPERTY;
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
public class RealtimeBoundSpecificationTestUtils {

    public static void testSetRealtimeStart(
        RealtimeBoundSpecification realtimeBoundBean,
        Flag flag
    ) {
        SerializableBean serializableBean = (SerializableBean) realtimeBoundBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            REALTIME_START_PROPERTY,
            null,
            TEST_DATE
        );

        realtimeBoundBean.setRealtimeStart(TEST_DATE);

        assertTrue (flag.isValue());
    }

    public static void testSetRealtimeEnd(
        RealtimeBoundSpecification realtimeBoundBean,
        Flag flag
    ) {
        SerializableBean serializableBean = (SerializableBean) realtimeBoundBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            REALTIME_END_PROPERTY,
            null,
            TEST_DATE
        );

        realtimeBoundBean.setRealtimeEnd(TEST_DATE);

        assertTrue (flag.isValue());
    }
}
