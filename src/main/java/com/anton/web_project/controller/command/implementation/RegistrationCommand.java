package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.UserService;
import com.anton.web_project.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = ResponsePagePath.REGISTRATION;
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        String password = request.getParameter(ServletAttribute.PASSWORD_ATTRIBUTE);
        UserService service = UserServiceImplementation.getInstance();
        try {
            boolean wasRegistrationSuccessful = service.register(username, password);
            if (wasRegistrationSuccessful) {
                pagePath = ResponsePagePath.LOGIN;
            } else {
                request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.WRONG_PASSWORD_OR_USERNAME);
            }
        } catch (ServiceException e) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.WARN, "can't register");
        }
        return pagePath;
    }
}