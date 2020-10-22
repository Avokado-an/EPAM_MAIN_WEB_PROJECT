package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.entity.LanguageType;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.UserServiceImplementation;
import com.anton.web.model.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMAIL_VERIFICATION_LINK =
            " http://localhost:8080/EPAM_WEB_PROJECT_war_exploded/account_verification.jsp?username=%s&language=%s";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pagePath = PagePath.REGISTRATION;
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);
        String email = request.getParameter(Attribute.EMAIL);
        String language = defineLanguage(session);
        int languageId = LanguageType.valueOf(language.toUpperCase()).getLanguageId();
        UserService service = UserServiceImplementation.getInstance();
        PropertiesReader reader = PropertiesReader.getInstance();
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
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("Can't register", e);
        }
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        request.setAttribute(Attribute.LANGUAGE, language);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        return pagePath;
    }

    private MailSender sendMessage(String language, String email, String username) {
        PropertiesReader reader = PropertiesReader.getInstance();
        String emailSubject = reader.readUserTextProperty(language, Message.EMAIL_SUBJECT);
        String serverResponse = reader.readUserTextProperty(language, Message.EMAIL_MESSAGE);
        return new MailSender(email, emailSubject,
                serverResponse + String.format(EMAIL_VERIFICATION_LINK, username, language));
    }

    private String defineLanguage(HttpSession session) {
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (language == null) {
            language = Attribute.DEFAULT_ENGLISH_LANGUAGE;
        }
        return language;
    }
}