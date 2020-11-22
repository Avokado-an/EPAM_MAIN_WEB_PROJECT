package com.anton.gym.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PasswordEncoderTest {
    @Test
    public void encodePasswordValidTest() {
        String line1 = "abc";
        String line2 = "abc";
        String expected = PasswordEncoder.encodeString(line1);
        String actual = PasswordEncoder.encodeString(line2);
        assertEquals(expected, actual);
    }

    @Test
    public void encodePasswordInvalidTest() {
        String line1 = "abcd";
        String line2 = "abcc";
        String expected = PasswordEncoder.encodeString(line1);
        String actual = PasswordEncoder.encodeString(line2);
        assertNotEquals(expected, actual);
    }
}
