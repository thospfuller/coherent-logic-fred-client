package com.coherentlogic.fred.client.core.converters;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coherentlogic.fred.client.core.converters.OutputTypeEnumConverter;
import com.coherentlogic.fred.client.core.domain.OutputType;

/**
 * Unit test for the OutputTypeEnumConverter class.
 * 
 * @author <a href="mailto:support@coherentlogic.com">Support</a>
 *
 */
public class OutputTypeEnumConverterTest {

    private OutputTypeEnumConverter converter = null;

    @Before
    public void setUp() throws Exception {
        converter = new OutputTypeEnumConverter (OutputType.class);
    }

    @After
    public void tearDown() throws Exception {
        converter = null;
    }

    @Test
    public void testFromStringValidString1() {
        OutputType actual = (OutputType) converter.fromString(
            OutputType.observationsByRealTimePeriod.toString());

        assertEquals (OutputType.observationsByRealTimePeriod, actual);
    }

    @Test
    public void testFromStringValidString2() {
        OutputType actual = (OutputType) converter.fromString(
            OutputType.observationsByVintageDateAllObservations.toString());

        assertEquals (
            OutputType.observationsByVintageDateAllObservations, actual);
    }

    @Test
    public void testFromStringValidString3() {
        OutputType actual = (OutputType) converter.fromString(
            OutputType
            .observationsByVintageDateNewAndRevisedObservationsOnly
            .toString());

        assertEquals (
            OutputType.observationsByVintageDateNewAndRevisedObservationsOnly,
            actual);
    }

    @Test
    public void testFromStringValidString4() {
        OutputType actual = (OutputType) converter.fromString(
            OutputType.observationsInitialReleaseOnly.toString());

        assertEquals (OutputType.observationsInitialReleaseOnly, actual);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFromStringInvalidString() {
        converter.fromString("Hello world!");
    }
}
