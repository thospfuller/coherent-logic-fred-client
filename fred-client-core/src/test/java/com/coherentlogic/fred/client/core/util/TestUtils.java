package com.coherentlogic.fred.client.core.util;

import static com.coherentlogic.coherent.data.model.core.util.Utils.using;
import static com.coherentlogic.fred.client.core.util.Constants.DEFAULT_LIMIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;
import com.coherentlogic.fred.client.core.domain.Frequency;

/**
 * This class contains helper methods that apply strictly to the domain package.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class TestUtils {

    public static final Date TEST_DATE = using (2014, 02, 21);

    public static final int TEST_INT = DEFAULT_LIMIT * 3;

    public static final long TEST_LONG = (long) TEST_INT;

    public static final String TEST_STRING = "fooBarBazBoo";
    
    public static final Frequency TEST_FREQUENCY = Frequency.a;

    public static PropertyChangeListener createPropertyChangeListener (
        final Flag flag,
        final String propertyName,
        final Object expectedOldValue,
        final Object expectedNewValue
    ) {
        return new PropertyChangeListener () {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                flag.setValue(true);
                assertEquals (
                    propertyName,
                    event.getPropertyName());
                assertEquals (expectedOldValue, event.getOldValue());
                assertEquals (
                    expectedNewValue,
                    event.getNewValue());
            }
        };
    }

    public static void createAndAssignPropertyChangeListener (
        SerializableBean defaultObject,
        final Flag flag,
        final String propertyName,
        final Object expectedOldValue,
        final Object expectedNewValue
    ) {
        PropertyChangeListener propertyChangeListener =
            createPropertyChangeListener (
                flag,
                propertyName,
                expectedOldValue,
                expectedNewValue
            );

        defaultObject.addPropertyChangeListener (propertyChangeListener);
    }

    public static <T extends SerializableBean> void testSetCount(
        T serializableBean,
        Flag flag,
        String propertyName,
        int expectedNewValue,
        Action<T> action
    ) {
        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            propertyName,
            0,
            expectedNewValue
        );

        action.execute(serializableBean);

        assertTrue (flag.isValue());
    }

    public static <T extends SerializableBean> void testSetterMethod (
        T defaultObject,
        Flag flag,
        String propertyName,
        Object expectedOldValue,
        Object expectedNewValue,
        Action<T> action
    ) {
        createAndAssignPropertyChangeListener(
            defaultObject,
            flag,
            propertyName,
            expectedOldValue,
            expectedNewValue
        );

        action.execute(defaultObject);

        assertTrue (flag.isValue());
    }
}
