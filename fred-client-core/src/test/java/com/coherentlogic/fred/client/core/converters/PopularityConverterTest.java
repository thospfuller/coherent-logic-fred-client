package com.coherentlogic.fred.client.core.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.converters.PopularityConverter;

/**
 * Unit test for the PopularityConverter class.
 *
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class PopularityConverterTest {

    private PopularityConverter converter = null;

    @Before
    public void setUp() throws Exception {
        converter = new PopularityConverter ();
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
    }

    @Test
    public void testFromStringWithValidNumber() {

        String validText = "39";

        Integer expected = Integer.valueOf(validText);

        Integer actual = (Integer) converter.fromString(validText);

        assertEquals (expected, actual);
    }

    @Test
    public void testFromStringWithEmptyString() {

        String validText = "";

        Integer actual = (Integer) converter.fromString(validText);

        assertNull (actual);
    }
}
