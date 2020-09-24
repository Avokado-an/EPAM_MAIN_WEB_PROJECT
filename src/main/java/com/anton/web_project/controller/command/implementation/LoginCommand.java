package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.entity.type.UserType;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.UserService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;
import com.anton.web_project.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static com.anton.web_project.controller.response.ServletAttribute.*;
import static com.anton.web_project.controller.response.ServletMessage.USER_NOT_ACTIVE;
import static com.anton.web_project.controller.response.ServletMessage.WRONG_PASSWORD_OR_USERNAME;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = ResponsePagePath.LOGIN;
        String username = request.getParameter(USERNAME_ATTRIBUTE);
        String password = request.getParameter(PASSWORD_ATTRIBUTE);
        UserService service = UserServiceImplementation.getInstance();
        try {
            Optional<User> currentUser = service.logIn(username, password);
            if (currentUser.isPresent()) {
                if (currentUser.get().isActive()) {
                    setSessionAttributes(currentUser.get(), request);
                    pagePath = chooseWorkingPage(request, currentUser.get().getType());
                } else {
                    request.setAttribute(MESSAGE_ATTRIBUTE, USER_NOT_ACTIVE);
                }
            } else {
                request.setAttribute(MESSAGE_ATTRIBUTE, WRONG_PASSWORD_OR_USERNAME);
            }
        } catch (ServiceException ex) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.WARN, "can't log in");
        }
        return pagePath;
    }

    private void setSessionAttributes(User currentUser, HttpServletRequest request) {
        UserType userType = currentUser.getType();
        HttpSession session = request.getSession();
        String language = currentUser.getLanguage();
        String username = currentUser.getUsername();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language);
        session.setAttribute(USERNAME_ATTRIBUTE, username);
        session.setAttribute(USER_ROLE_ATTRIBUTE, userType);
    }

    private String chooseWorkingPage(HttpServletRequest request, UserType type) throws ServiceException {
        String pagePath = ResponsePagePath.USER_ACTION;
        if (type == UserType.ADMIN) {
            AdminService service = AdminServiceImplementation.getInstance();
            List<User> users = service.viewUsers();
            request.setAttribute(USERS, users);
            pagePath = ResponsePagePath.VIEW_USERS;
            LOGGER.log(Level.INFO, "Admin signed in");
        } else {
            LOGGER.log(Level.INFO, "User signed in");
        }
        return pagePath;
    }
}
