package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Message;
import com.anton.gym.exception.TransactionException;
import com.anton.gym.util.PropertiesReader;
import com.anton.gym.model.entity.LanguageType;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMAIL_VERIFICATION_LINK =
            " http://localhost:8080/EPAM_WEB_PROJECT_war_exploded/userServlet?command=verify_account&username=%s&language=%s";
    private UserService service = UserServiceImplementation.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pagePath = PagePath.REGISTRATION;
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);
        String email = request.getParameter(Attribute.EMAIL);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        int languageId = LanguageType.valueOf(language.toUpperCase()).getLanguageId();
        String serverResponse;
        MailSender sender = sendMessage(language, email, username);
        try {
            if (!service.isUsernameTaken(username)) {
                boolean wasRegistrationSuccessful = service.register(username, password, email, languageId);
                if (wasRegistrationSuccessful) {
                    sender.send();
                    pagePath = PagePath.LOGIN;
                } else {
                    serverResponse = reader.readUserTextProperty(language, Message.WRONG_PASSWORD_OR_USERNAME_OR_EMAIL);
                    request.setAttribute(Attribute.MESSAGE, serverResponse);
                }
            } else {
                serverResponse = reader.readUserTextProperty(language, Message.USERNAME_IS_TAKEN);
                request.setAttribute(Attribute.MESSAGE, serverResponse);
            }
        } catch (ServiceException | TransactionException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("Can't register", e);
        }
        return pagePath;
    }

    private MailSender sendMessage(String language, String email, String username) {
        PropertiesReader reader = PropertiesReader.getInstance();
        String emailSubject = reader.readUserTextProperty(language, Message.EMAIL_SUBJECT);
        String serverResponse = reader.readUserTextProperty(language, Message.EMAIL_MESSAGE);
        return new MailSender(email, emailSubject,
                serverResponse + String.format(EMAIL_VERIFICATION_LINK, username, language));
    }
}