package com.coherentlogic.fred.client.core.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrequencyTest {
    
    public FrequencyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fromString method, of class Frequency.
     */
    @Test
    public void testFromString() {
        assertEquals(Frequency.daily, Frequency.fromString("d"));
        
        for (Frequency f : Frequency.values()) {
            assertEquals(f, Frequency.fromString(f.toString()));
        }
    }
    
}
