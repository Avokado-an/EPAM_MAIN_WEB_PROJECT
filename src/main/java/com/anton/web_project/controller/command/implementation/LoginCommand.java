package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;
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

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = ResponsePagePath.LOGIN;
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        String password = request.getParameter(ServletAttribute.PASSWORD_ATTRIBUTE);
        UserService service = UserServiceImplementation.getInstance();
        try {
            Optional<User> currentUser = service.logIn(username, password);
            if (currentUser.isPresent()) {
                UserType userType = currentUser.get().getType();
                HttpSession session = request.getSession();
                session.setAttribute(ServletAttribute.USERNAME_ATTRIBUTE, username);
                session.setAttribute(ServletAttribute.USER_ROLE_ATTRIBUTE, userType);
                pagePath = chooseWorkingPage(request, userType);
                request.setAttribute(ServletAttribute.USERNAME_ATTRIBUTE, session.getAttribute(ServletAttribute.USERNAME_ATTRIBUTE));
            } else {
                request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.WRONG_PASSWORD_OR_USERNAME);
            }
        } catch (ServiceException ex) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.WARN, "can't log in");
        }
        return pagePath;
    }

    private String chooseWorkingPage(HttpServletRequest request, UserType type) throws ServiceException {
        String pagePath = ResponsePagePath.USER_ACTION;
        if (type == UserType.ADMIN) {
            AdminService service = AdminServiceImplementation.getInstance();
            List<User> users = service.viewUsers();
            request.setAttribute(ServletAttribute.USERS, users);
            pagePath = ResponsePagePath.VIEW_USERS;
            LOGGER.log(Level.INFO, "Admin signed in");
        } else {
            LOGGER.log(Level.INFO, "User signed in");
        }
        return pagePath;
    }
}
