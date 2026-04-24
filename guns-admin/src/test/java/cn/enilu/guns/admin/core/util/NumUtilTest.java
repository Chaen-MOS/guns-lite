package cn.enilu.guns.admin.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * NumUtil unit tests
 * Tests the numeric formatting utility without external dependencies
 */
@RunWith(MockitoJUnitRunner.class)
public class NumUtilTest {

    private double testDouble1;
    private double testDouble2;
    private double zeroValue;

    @Before
    public void setUp() {
        testDouble1 = 123.456789;
        testDouble2 = 50.5;
        zeroValue = 0.0;
    }

    /**
     * Test keepRandomPoint with 2 decimal places
     */
    @Test
    public void testKeepRandomPointWithTwoDecimals() {
        String result = NumUtil.keepRandomPoint(testDouble1, 2);
        assertNotNull("Result should not be null", result);
        assertEquals("Should keep 2 decimal places", "123.46", result);
    }

    /**
     * Test keepRandomPoint with 3 decimal places
     */
    @Test
    public void testKeepRandomPointWithThreeDecimals() {
        String result = NumUtil.keepRandomPoint(testDouble1, 3);
        assertNotNull("Result should not be null", result);
        assertEquals("Should keep 3 decimal places", "123.457", result);
    }

    /**
     * Test keep2Point method
     */
    @Test
    public void testKeep2Point() {
        String result = NumUtil.keep2Point(testDouble1);
        assertNotNull("Result should not be null", result);
        assertEquals("Should keep 2 decimal places", "123.46", result);
    }

    /**
     * Test keep1Point method
     */
    @Test
    public void testKeep1Point() {
        String result = NumUtil.keep1Point(testDouble1);
        assertNotNull("Result should not be null", result);
        assertEquals("Should keep 1 decimal place", "123.5", result);
    }

    /**
     * Test keepRandomPoint with zero decimal places
     */
    @Test
    public void testKeepRandomPointWithZeroDecimals() {
        String result = NumUtil.keepRandomPoint(testDouble1, 0);
        assertNotNull("Result should not be null", result);
        assertEquals("Should round to integer", "123", result);
    }

    /**
     * Test keepRandomPoint with null value
     */
    @Test
    public void testKeepRandomPointWithNullValue() {
        String result = NumUtil.keepRandomPoint(null, 2);
        assertNotNull("Null value should return formatted 0.00", result);
        assertEquals("Null should be treated as 0.00", "0.00", result);
    }

    /**
     * Test keep2PointZero method
     */
    @Test
    public void testKeep2PointZero() {
        String result = NumUtil.keep2PointZero(testDouble1);
        assertNotNull("Result should not be null", result);
        // Should include trailing zeros
        assertTrue("Should format with zeros", result.length() > 0);
    }

    /**
     * Test keep2PointZero with simple value
     */
    @Test
    public void testKeep2PointZeroWithSimpleValue() {
        String result = NumUtil.keep2PointZero(testDouble2);
        assertNotNull("Result should not be null", result);
        assertTrue("Should contain decimal point", result.contains("."));
    }

    /**
     * Test percent2Point method
     */
    @Test
    public void testPercent2Point() {
        String result = NumUtil.percent2Point(0.5);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain percent sign", result.contains("%"));
    }

    /**
     * Test percent2Point with zero
     */
    @Test
    public void testPercent2PointWithZero() {
        String result = NumUtil.percent2Point(0.0);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain percent sign", result.contains("%"));
    }

    /**
     * Test percentRandomPoint with various decimal places
     */
    @Test
    public void testPercentRandomPointWithVariousDecimals() {
        String result = NumUtil.percentRandomPoint(0.25, 2);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should contain percent sign", result.contains("%"));
    }

    /**
     * Test keepRandomPoint with very small decimal
     */
    @Test
    public void testKeepRandomPointWithVerySmallDecimal() {
        String result = NumUtil.keepRandomPoint(0.00001, 4);
        assertNotNull("Result should not be null", result);
        assertEquals("Should keep 4 decimal places", "0.0000", result);
    }

    /**
     * Test keepRandomPoint with large number
     */
    @Test
    public void testKeepRandomPointWithLargeNumber() {
        String result = NumUtil.keepRandomPoint(9999999.99999, 2);
        assertNotNull("Result should not be null", result);
        assertEquals("Should handle large numbers", "10000000.00", result);
    }

    /**
     * Test keep1Point with zero
     */
    @Test
    public void testKeep1PointWithZero() {
        String result = NumUtil.keep1Point(0.0);
        assertNotNull("Result should not be null", result);
        assertEquals("Zero should format to 0.0", "0.0", result);
    }

    /**
     * Test keep2Point with negative number
     */
    @Test
    public void testKeep2PointWithNegativeNumber() {
        String result = NumUtil.keep2Point(-123.456);
        assertNotNull("Result should not be null", result);
        assertEquals("Should handle negative numbers", "-123.46", result);
    }
}
