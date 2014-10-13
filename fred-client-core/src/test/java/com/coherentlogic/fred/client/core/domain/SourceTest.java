package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.LINK_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.NAME_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Source} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class SourceTest {

    private final Flag flag = new Flag ();

    private Source source = null;
    
    @Before
    public void setUp() throws Exception {
        source = new Source ();
    }

    @After
    public void tearDown() throws Exception {
        source = null;
        flag.setValue(false);
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(source, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(source, flag);
    }

    @Test
    public void testSetName() {
        testSetterMethod(
            source,
            flag,
            NAME_PROPERTY,
            null,
            TEST_STRING,
            new Action<Source> () {
                @Override
                public void execute(Source data) {
                    data.setName(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetLink() {
        testSetterMethod(
            source,
            flag,
            LINK_PROPERTY,
            null,
            TEST_STRING,
            new Action<Source> () {
                @Override
                public void execute(Source data) {
                    data.setLink(TEST_STRING);
                }
            }
        );
    }
}
