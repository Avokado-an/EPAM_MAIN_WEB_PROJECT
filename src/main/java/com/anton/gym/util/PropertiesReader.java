package com.anton.gym.util;

import com.anton.gym.controller.command.Message;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The {@code PropertiesReader} class represents PropertiesReader.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class PropertiesReader {
    private static final String SERVER_MESSAGE_PROPERTY = "property.text";
    private static final String UNDERSCORE = "_";
    private static PropertiesReader instance;

    private PropertiesReader() {

    }

    /**
     * get instance of the class
     * @return instance
     */
    public static PropertiesReader getInstance() {
        if(instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    /**
     * reads text property for response
     * @param currentLanguage the language of response
     * @param message the message by which response text if defined
     * @return text from properties file
     */
    public String readUserTextProperty(String currentLanguage, Message message) {
        String[] languageAndCountry = currentLanguage.split(UNDERSCORE);
        String language = languageAndCountry[0];
        String country = languageAndCountry[1];
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle(SERVER_MESSAGE_PROPERTY, current);
        return rb.getString(message.name().toLowerCase());
    }
}
