package com.anton.web.controller.util;

import com.anton.web.controller.command.Message;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesReader {
    private static final String SERVER_MESSAGE_PROPERTY = "html.text";
    private static final String UNDERSCORE = "_";
    private static PropertiesReader instance;

    private PropertiesReader() {

    }

    public static PropertiesReader getInstance() {
        if(instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public String readUserTextProperty(String currentLanguage, Message message) {
        String[] languageAndCountry = currentLanguage.split(UNDERSCORE);
        String language = languageAndCountry[0];
        String country = languageAndCountry[1];
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle(SERVER_MESSAGE_PROPERTY, current);
        return rb.getString(message.name().toLowerCase());
    }
}
