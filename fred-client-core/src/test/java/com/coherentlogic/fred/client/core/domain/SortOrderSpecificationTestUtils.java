package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.SortOrderSpecification.SORT_ORDER_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Class contains helper methods for testing the {@link SortOrderSpecification}
 * class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SortOrderSpecificationTestUtils {

    public static void testSetSortOrder(
        SortOrderSpecification sortOrderBean,
        Flag flag
    ) {
        testSetSortOrder(
            sortOrderBean,
            flag,
            SortOrder.asc
        );
    }

    public static void testSetSortOrder(
            SortOrderSpecification sortOrderBean,
            Flag flag,
            SortOrder expectedOldValue
        ) {
            SerializableBean serializableBean = (SerializableBean) sortOrderBean;

            createAndAssignPropertyChangeListener(
                serializableBean,
                flag,
                SORT_ORDER_PROPERTY,
                expectedOldValue,
                SortOrder.desc
            );

            sortOrderBean.setSortOrder(SortOrder.desc);

            assertTrue (flag.isValue());
        }
}
