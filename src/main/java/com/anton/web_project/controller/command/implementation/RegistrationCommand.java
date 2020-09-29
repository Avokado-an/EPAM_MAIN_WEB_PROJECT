package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.UserService;
import com.anton.web_project.model.service.impl.UserServiceImplementation;
import com.anton.web_project.model.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMAIL_SUBJECT = "Account confirmation";
    private static final String EMAIL_MESSAGE = "Verify your account by this link" +
            " http://localhost:8080/EPAM_WEB_PROJECT_war_exploded/account_verification.jsp?username=";

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = ResponsePagePath.REGISTRATION;
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        String password = request.getParameter(ServletAttribute.PASSWORD_ATTRIBUTE);
        String email = request.getParameter(ServletAttribute.EMAIL_ATTRIBUTE);
        String language = request.getParameter(ServletAttribute.LANGUAGE_ATTRIBUTE);
        int languageId = Integer.parseInt(language);
        UserService service = UserServiceImplementation.getInstance();
        MailSender sender = new MailSender(email, EMAIL_SUBJECT, EMAIL_MESSAGE + username);
        try {
            if (!service.isUsernameTaken(username)) {
                boolean wasRegistrationSuccessful = service.register(username, password, email, languageId);
                if (wasRegistrationSuccessful) {
                    sender.send();
                    pagePath = ResponsePagePath.LOGIN;
                } else {
                    request.setAttribute(
                            ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.WRONG_PASSWORD_OR_USERNAME_OR_EMAIL);
                }
            } else {
                request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.USERNAME_IS_TAKEN);
            }
        } catch (ServiceException e) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.warn("Can't register");
        }
        HttpSession session = request.getSession();
        request.setAttribute(
                ServletAttribute.LANGUAGE_ATTRIBUTE, session.getAttribute(ServletAttribute.LANGUAGE_ATTRIBUTE));
        return pagePath;
    }
}