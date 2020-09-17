package com.anton.web_project.model.service.impl;

import com.anton.web_project.model.service.MailService;

public class MailServiceImplementation implements MailService {
    /*private static MailService instance = new MailServiceImplementation();

    public static MailService getInstance() {
        return instance;
    }

    private MailServiceImplementation() {
        String to = "fromaddress@gmail.com";
        String from = "toaddress@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    public void send(String email, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }*/
}
