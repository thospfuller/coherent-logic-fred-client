package com.coherentlogic.fred.client.core.converters;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.domain.Observations;

/**
 * Unit test for the {@link SwingPropertyChangeSupportConverter} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SwingPropertyChangeSupportConverterTest {

    private SwingPropertyChangeSupportConverter converter = null;
    
    @Before
    public void setUp() throws Exception {
        converter = new SwingPropertyChangeSupportConverter(null, null, true);
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
    }

    @Test
    public void testCanConvertClassShouldPass() {
        assertTrue (converter.canConvert(Observations.class));
    }

    @Test
    public void testCanConvertClassShouldFail() {
        assertFalse (converter.canConvert(BigDecimal.class));
    }
}
