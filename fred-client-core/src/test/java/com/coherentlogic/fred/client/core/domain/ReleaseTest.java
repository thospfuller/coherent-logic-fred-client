package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.PropertyNames.LINK_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.NAME_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.PropertyNames.PRESS_RELEASE_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link Release} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ReleaseTest {

    private final Flag flag = new Flag ();

    private Release release = null;

    @Before
    public void setUp() throws Exception {
        release = new Release ();
    }

    @After
    public void tearDown() throws Exception {
        release = null;
        flag.setValue(false);
    }

    @Test
    public void testSetRealtimeStart() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeStart(release, flag);
    }

    @Test
    public void testSetRealtimeEnd() {
        RealtimeBoundSpecificationTestUtils.testSetRealtimeEnd(release, flag);
    }

    @Test
    public void testSetName() {
        testSetterMethod(
            release,
            flag,
            NAME_PROPERTY,
            null,
            TEST_STRING,
            new Action<Release> () {
                @Override
                public void execute(Release data) {
                    data.setName(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetPressRelease() {
        testSetterMethod(
            release,
            flag,
            PRESS_RELEASE_PROPERTY,
            false,
            true,
            new Action<Release> () {
                @Override
                public void execute(Release data) {
                    data.setPressRelease(true);
                }
            }
        );
    }

    @Test
    public void testSetLink() {
        testSetterMethod(
            release,
            flag,
            LINK_PROPERTY,
            null,
            TEST_STRING,
            new Action<Release> () {
                @Override
                public void execute(Release data) {
                    data.setLink (TEST_STRING);
                }
            }
        );
    }
}
