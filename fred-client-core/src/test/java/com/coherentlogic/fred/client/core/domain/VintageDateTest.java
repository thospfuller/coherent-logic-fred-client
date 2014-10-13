package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.createAndAssignPropertyChangeListener;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link VintageDate} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class VintageDateTest {

    private final Flag flag = new Flag ();

    private VintageDate vintageDate = null;

    @Before
    public void setUp() throws Exception {
        vintageDate = new VintageDate();
    }

    @After
    public void tearDown() throws Exception {
        vintageDate = null;
        flag.setValue(false);
    }

    @Test
    public void testSetVintageDate() {
        createAndAssignPropertyChangeListener(
            vintageDate,
            flag,
            VintageDate.VINTAGE_DATE,
            null,
            TEST_DATE
        );

        vintageDate.setVintageDate(TEST_DATE);

        assertTrue (flag.isValue());
    }
}
