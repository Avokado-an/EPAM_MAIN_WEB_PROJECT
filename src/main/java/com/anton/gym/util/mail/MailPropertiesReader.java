package com.anton.gym.util.mail;

import java.util.Properties;

public class MailPropertiesReader {
    private static final String NAME_PROPERTY = "mail.user.name";
    private static final String PASSWORD_PROPERTY = "mail.user.password";

    public static String defineNameProperty (Properties configProperties) {
        return configProperties.getProperty(NAME_PROPERTY);
    }

    public static String definePasswordProperty (Properties configProperties) {
        return configProperties.getProperty(PASSWORD_PROPERTY);
    }
}
