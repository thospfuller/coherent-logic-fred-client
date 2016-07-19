package com.coherentlogic.fred.client.core.converters;

import static com.coherentlogic.fred.client.core.util.Constants.FREQUENCY;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.converters.OrderByEnumConverter;
import com.coherentlogic.fred.client.core.domain.OrderBy;

/**
 * Unit test for the OrderByEnumConverter class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class OrderByEnumConverterTest {

    private OrderByEnumConverter orderByEnumConverter = null;

    @Before
    public void setUp() throws Exception {
        orderByEnumConverter = new OrderByEnumConverter (OrderBy.class);
    }

    @After
    public void tearDown() throws Exception {
        orderByEnumConverter = null;
    }

    @Test
    public void testFromStringFrequency() {

        OrderBy orderBy = (OrderBy) orderByEnumConverter.fromString(FREQUENCY);

        assertEquals (OrderBy.frequency, orderBy);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFromStringUsingInvalidString() {
        orderByEnumConverter.fromString("fubar");
    }
}

