package com.anton.web.model.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {
    private static final String NAME_PROPERTY = "mail.user.name";
    private static final String PASSWORD_PROPERTY = "mail.user.password";

    private SessionFactory() {

    }

    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(NAME_PROPERTY);
        String userPassword = configProperties.getProperty(PASSWORD_PROPERTY);
        return Session.getDefaultInstance(configProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
