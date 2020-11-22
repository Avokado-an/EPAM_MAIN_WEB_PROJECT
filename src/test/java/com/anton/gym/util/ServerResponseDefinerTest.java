package com.anton.gym.util;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ServerResponseDefinerTest {
    @Test
    public void defineServerResponseValidTest() {
        String language = "en_Us";
        boolean condition = true;
        String actual = ServerResponseDefiner.defineServerResponse(condition, language);
        String expected = "Action performed successfully";
        assertEquals(actual, expected);
    }

    @Test
    public void defineServerResponseInvalidTest() {
        String language = "ru_Ru";
        boolean condition = false;
        String actual = ServerResponseDefiner.defineServerResponse(condition, language);
        String expected = " Action performed successfully";
        assertNotEquals(actual, expected);
    }
}
