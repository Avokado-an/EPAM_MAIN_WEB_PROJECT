package com.anton.gym.util;

import com.anton.gym.controller.command.Message;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PropertiesReaderTest {
    @Test
    public void readUserTextPropertyValidTest() {
        String language = "en_Us";
        Message message = Message.ABOUT_ME;
        String expected = "about me...";
        String actual = PropertiesReader.getInstance().readUserTextProperty(language, message);
        assertEquals(expected, actual);
    }

    @Test
    public void readUserTextPropertyInvalidTest() {
        String language = "en_Us";
        Message message = Message.ABOUT_ME;
        String expected = "client";
        String actual = PropertiesReader.getInstance().readUserTextProperty(language, message);
        assertNotEquals(expected, actual);
    }
}
