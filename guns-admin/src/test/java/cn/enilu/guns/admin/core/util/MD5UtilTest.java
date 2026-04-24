package cn.enilu.guns.admin.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * MD5Util unit tests
 * Tests the MD5 encryption utility without external dependencies
 */
@RunWith(MockitoJUnitRunner.class)
public class MD5UtilTest {

    private String testString1;
    private String testString2;
    private String testPassword;

    @Before
    public void setUp() {
        testString1 = "hello";
        testString2 = "world";
        testPassword = "123456";
    }

    /**
     * Test that encrypt method returns a hash for valid input
     */
    @Test
    public void testEncryptReturnsHashForValidInput() {
        String result = MD5Util.encrypt(testString1);
        assertNotNull("Encrypted result should not be null", result);
        assertTrue("Encrypted result should not be empty", result.length() > 0);
        assertEquals("MD5 hash should be 32 characters", 32, result.length());
    }

    /**
     * Test that encrypt method produces consistent results
     */
    @Test
    public void testEncryptProducesConsistentResults() {
        String result1 = MD5Util.encrypt(testString1);
        String result2 = MD5Util.encrypt(testString1);
        assertEquals("Same input should produce same output", result1, result2);
    }

    /**
     * Test that different inputs produce different hashes
     */
    @Test
    public void testDifferentInputsProduceDifferentHashes() {
        String result1 = MD5Util.encrypt(testString1);
        String result2 = MD5Util.encrypt(testString2);
        assertNotEquals("Different inputs should produce different hashes", result1, result2);
    }

    /**
     * Test that empty string produces a valid hash
     */
    @Test
    public void testEncryptEmptyString() {
        String result = MD5Util.encrypt("");
        assertNotNull("Empty string should still be encrypted", result);
        assertEquals("MD5 hash should always be 32 characters", 32, result.length());
    }

    /**
     * Test MD5 hash of a password
     */
    @Test
    public void testEncryptPassword() {
        String encrypted = MD5Util.encrypt(testPassword);
        assertNotNull("Password encryption should return valid hash", encrypted);
        assertEquals("Password hash should be 32 characters", 32, encrypted.length());
        // Verify the hash is lowercase hexadecimal
        assertTrue("Hash should only contain hex characters", encrypted.matches("[a-f0-9]{32}"));
    }

    /**
     * Test that encrypt method returns lowercase hex
     */
    @Test
    public void testEncryptReturnsLowercaseHex() {
        String result = MD5Util.encrypt(testString1);
        assertEquals("Hash should be lowercase", result, result.toLowerCase());
        assertTrue("Hash should only contain hex characters", result.matches("[a-f0-9]{32}"));
    }

    /**
     * Test with special characters
     */
    @Test
    public void testEncryptWithSpecialCharacters() {
        String specialString = "test@#$%^&*()";
        String result = MD5Util.encrypt(specialString);
        assertNotNull("Special characters should be handled", result);
        assertEquals("MD5 hash should be 32 characters regardless of input", 32, result.length());
    }

    /**
     * Test with numbers
     */
    @Test
    public void testEncryptWithNumbers() {
        String numberString = "1234567890";
        String result = MD5Util.encrypt(numberString);
        assertNotNull("Numbers should be encrypted", result);
        assertEquals("MD5 hash should be 32 characters", 32, result.length());
    }

    /**
     * Test with unicode characters
     */
    @Test
    public void testEncryptWithUnicodeCharacters() {
        String unicodeString = "你好世界";
        String result = MD5Util.encrypt(unicodeString);
        assertNotNull("Unicode characters should be encrypted", result);
        assertEquals("MD5 hash should be 32 characters for unicode", 32, result.length());
    }

    /**
     * Test with very long string
     */
    @Test
    public void testEncryptWithLongString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("a");
        }
        String longString = sb.toString();
        String result = MD5Util.encrypt(longString);
        assertNotNull("Long string should be encrypted", result);
        assertEquals("MD5 hash should always be 32 characters", 32, result.length());
    }
}
