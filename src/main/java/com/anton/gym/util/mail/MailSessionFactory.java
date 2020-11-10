package com.anton.gym.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class MailSessionFactory {
    private MailSessionFactory() {

    }

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
