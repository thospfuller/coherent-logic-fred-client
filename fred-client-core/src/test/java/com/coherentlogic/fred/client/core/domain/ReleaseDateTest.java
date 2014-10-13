package com.coherentlogic.fred.client.core.domain;

import static com.coherentlogic.fred.client.core.domain.ReleaseDate.DATE_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.ReleaseDate.RELEASE_ID_PROPERTY;
import static com.coherentlogic.fred.client.core.domain.ReleaseDate.RELEASE_NAME_PROPERTY;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_DATE;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_LONG;
import static com.coherentlogic.fred.client.core.util.TestUtils.TEST_STRING;
import static com.coherentlogic.fred.client.core.util.TestUtils.testSetterMethod;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.coherent.data.model.core.util.Action;
import com.coherentlogic.coherent.data.model.core.util.Flag;

/**
 * Unit test for the {@link ReleaseDate} class.
 *
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 */
public class ReleaseDateTest {

    private final Flag flag = new Flag ();

    private ReleaseDate releaseDate = null;

    @Before
    public void setUp() throws Exception {
        releaseDate = new ReleaseDate();
    }

    @After
    public void tearDown() throws Exception {
        releaseDate = null;
    }

    @Test
    public void testSetReleaseName() {
        testSetterMethod(
            releaseDate,
            flag,
            RELEASE_NAME_PROPERTY,
            null,
            TEST_STRING,
            new Action<ReleaseDate> () {
                @Override
                public void execute(ReleaseDate data) {
                    releaseDate.setReleaseName(TEST_STRING);
                }
            }
        );
    }

    @Test
    public void testSetReleaseId() {
        testSetterMethod(
            releaseDate,
            flag,
            RELEASE_ID_PROPERTY,
            null,
            TEST_LONG,
            new Action<ReleaseDate> () {
                @Override
                public void execute(ReleaseDate data) {
                    releaseDate.
                        setReleaseId(TEST_LONG);
                }
                
            }
        );
    }

    @Test
    public void testSetDate() {
        testSetterMethod(
            releaseDate,
            flag,
            DATE_PROPERTY,
            null,
            TEST_DATE,
            new Action<ReleaseDate> () {
                @Override
                public void execute(ReleaseDate data) {
                    releaseDate.setDate(TEST_DATE);
                }
            }
        );
    }
}
