package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.LIMIT_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PaginationSpecification.OFFSET_PROPERTY;
import static com.coherentlogic.fred.client.core.util.Constants.DEFAULT_LIMIT;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_INT;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import com.coherentlogic.coherent.data.model.core.domain.SerializableBean;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Class contains helper methods for testing the {@link PaginationSpecification}
 * class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class PaginationSpecificationTestUtils {

    public static void testSetLimit(
        PaginationSpecification paginationBean,
        Flag flag
    ) {
        testSetLimit(
            paginationBean,
            flag,
            Long.valueOf(DEFAULT_LIMIT)
        );
    }

    public static void testSetLimit(
        PaginationSpecification paginationBean,
        Flag flag,
        Long expectedOldValue
    ) {
        SerializableBean serializableBean = (SerializableBean) paginationBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            LIMIT_PROPERTY,
            expectedOldValue,
            Long.valueOf(TEST_INT)
        );

        paginationBean.setLimit(TEST_INT);

        assertTrue (flag.isValue());
    }

    public static void testSetOffset(
        PaginationSpecification paginationBean,
        Flag flag
    ) {
        SerializableBean serializableBean = (SerializableBean) paginationBean;

        createAndAssignPropertyChangeListener(
            serializableBean,
            flag,
            OFFSET_PROPERTY,
            0,
            TEST_INT
        );

        paginationBean.setOffset (TEST_INT);

        assertTrue (flag.isValue());
    }
}
