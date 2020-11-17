package com.anton.gym.util.mail;

import java.util.Properties;

/**
 * The {@code MailPropertiesReader} class represents MailPropertiesReader.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MailPropertiesReader {
    private static final String NAME_PROPERTY = "mail.user.name";
    private static final String PASSWORD_PROPERTY = "mail.user.password";

    /**
     * define property 'name'
     * @param configProperties the configuration properties
     * @return value of name from properties file
     */
    public static String defineNameProperty (Properties configProperties) {
        return configProperties.getProperty(NAME_PROPERTY);
    }

    /**
     * define property 'password'
     * @param configProperties the configuration properties
     * @return value of password from properties file
     */
    public static String definePasswordProperty (Properties configProperties) {
        return configProperties.getProperty(PASSWORD_PROPERTY);
    }
}
