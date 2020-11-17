package com.anton.gym.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * The {@code MailSessionFactory} class represents MailSessionFactory.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MailSessionFactory {
    private MailSessionFactory() {

    }

    /**
     * creates session
     * @param configProperties the configuration properties
     * @return created session
     */
    public static Session createSession(Properties configProperties) {
        String userName = MailPropertiesReader.defineNameProperty(configProperties);
        String userPassword = MailPropertiesReader.definePasswordProperty(configProperties);
        return Session.getDefaultInstance(configProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
