package com.anton.gym.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * The {@code MailSender} class represents MailSender.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PROPERTY_PATH =
            "C:\\Users\\Mi\\IdeaProjects\\EPAM_MAIN\\EPAM_WEB_PROJECT\\src\\main\\resources\\config\\mail.properties";
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    /**
     * creates object of MailSender
     * @param sendToEmail the email to which send the message
     * @param mailSubject the subject of the message
     * @param mailText the text of the message
     */
    public MailSender(String sendToEmail, String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        properties = new Properties();
        try {
            properties.load(new FileReader(PROPERTY_PATH));
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * sends a message
     */
    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            LOGGER.error(String.format("Invalid address: %s, error - %s", sendToEmail, e));
        } catch (MessagingException e) {
            LOGGER.error(String.format("Error generating or sending message: %s", e));
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = MailSessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}
