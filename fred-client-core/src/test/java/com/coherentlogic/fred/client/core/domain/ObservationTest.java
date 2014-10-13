package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.DATE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.VALUE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_END_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.RealtimeBoundSpecification.REALTIME_START_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Observation} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ObservationTest {

    private final Flag flag = new Flag ();

    private Observation observation = null;

    @Before
    public void setUp() throws Exception {
        observation = new Observation ();
    }

    @After
    public void tearDown() throws Exception {
        observation = null;
        flag.setValue (false);
    }

    @Test
    public void testSetRealtimeStart() {
        testSetterMethod(
            observation,
            flag,
            REALTIME_START_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observation> () {
                @Override
                public void execute(Observation data) {
                    observation.setRealtimeStart(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetRealtimeEnd() {
        testSetterMethod(
            observation,
            flag,
            REALTIME_END_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observation> () {
                @Override
                public void execute(Observation data) {
                    observation.setRealtimeEnd(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetDate() {
        testSetterMethod(
            observation,
            flag,
            DATE_PROPERTY,
            null,
            TEST_DATE,
            new Action<Observation> () {
                @Override
                public void execute(Observation data) {
                    observation.setDate(TEST_DATE);
                }
            }
        );
    }

    @Test
    public void testSetValue() {

        final BigDecimal value =
            BigDecimal.valueOf(TEST_INT);

        testSetterMethod(
            observation,
            flag,
            VALUE_PROPERTY,
            null,
            value,
            new Action<Observation> () {
                @Override
                public void execute(Observation data) {
                    observation.setValue(value);
                }
            }
        );
    }
}
