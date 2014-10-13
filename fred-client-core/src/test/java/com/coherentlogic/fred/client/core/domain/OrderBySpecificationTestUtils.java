package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.OrderBySpecification.ORDER_BY_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Class contains helper methods for testing the {@link OrderBySpecification}
 * class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class OrderBySpecificationTestUtils {

    static void testSetOrderBy (OrderBySpecification orderByBean, Flag flag) {
        testSetOrderBy (orderByBean, flag, null);
    }

    static void testSetOrderBy (
        OrderBySpecification orderByBean,
        Flag flag,
        OrderBy defaultOrderBy
    ) {

        SerializableBean serializableBean = (SerializableBean) orderByBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            ORDER_BY_PROPERTY,
            defaultOrderBy,
            OrderBy.lastUpdated
        );

        orderByBean.setOrderBy(OrderBy.lastUpdated);

        assertTrue (flag.isValue());
    }
}
