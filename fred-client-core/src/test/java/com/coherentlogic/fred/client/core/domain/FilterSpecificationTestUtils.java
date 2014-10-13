package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.FilterSpecification.FILTER_VALUE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.FilterSpecification.FILTER_VARIABLE_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Test utility methods that are shared with test classes that review
 * implementations of the {@link FilterSpecification} interface.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class FilterSpecificationTestUtils {

    public static void testSetFilterVariable(
        final FilterSpecification filterSpecification,
        final Flag flag
    ) {
        SerializableBean serializableBean = (SerializableBean) filterSpecification;

        testSetterMethod(
            serializableBean,
            flag,
            FILTER_VARIABLE_PROPERTY,
            null,
            FilterVariable.frequency,
            new Action<SerializableBean> () {
                @Override
                public void execute(SerializableBean data) {
                    filterSpecification.
                        setFilterVariable(FilterVariable.frequency);
                }
            }
        );
    }

    public static void testSetFilterValue(
        final FilterSpecification filterSpecification,
        final Flag flag
    ) {
        SerializableBean serializableBean =
            (SerializableBean) filterSpecification;

        testSetterMethod(
            serializableBean,
            flag,
            FILTER_VALUE_PROPERTY,
            null,
            FilterValue.regional,
            new Action<SerializableBean> () {
                @Override
                public void execute(SerializableBean data) {
                    filterSpecification.
                        setFilterValue(FilterValue.regional);
                }
            }
        );
    }
}
