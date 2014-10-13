package com.coherentlogic.fred.client.core.converters;

import static com.coherentlogic.fred.client.core.util.Constants.ASC;
import static com.coherentlogic.fred.client.core.util.Constants.DESC;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.domain.SortOrder;

/**
 * 
 * @author <a href="support@coherentlogic.com">Support</a>
 */
public class SortOrderEnumConverterTest {

    private SortOrderEnumConverter converter = null;

    @Before
    public void setUp() throws Exception {
        converter = new SortOrderEnumConverter (SortOrder.class);
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFromInvalidString() {
        converter.fromString("fubar");
    }

    @Test
    public void testFromValidString() {
        assertEquals (SortOrder.asc, converter.fromString(ASC));
    }

    @Test
    public void testFromValidString2() {
        assertEquals (SortOrder.desc, converter.fromString(DESC));
    }
}
