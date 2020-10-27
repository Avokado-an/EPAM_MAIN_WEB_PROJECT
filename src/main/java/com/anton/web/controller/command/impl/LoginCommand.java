package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.entity.Membership;
import com.anton.web.model.entity.User;
import com.anton.web.model.entity.UserType;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;
import com.anton.web.model.service.MembershipService;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.AdminServiceImplementation;
import com.anton.web.model.service.impl.MembershipServiceImplementation;
import com.anton.web.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();;

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath;
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);
        try {
            Optional<User> currentUser = userService.logIn(username, password);
            pagePath = processUserLoginAction(request, currentUser, username);
        } catch (ServiceException ex) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't log in", ex);
        }
        return pagePath;
    }

    private void setSessionAttributes(User currentUser, HttpServletRequest request) {
        UserType userType = currentUser.getType();
        HttpSession session = request.getSession();
        String language = currentUser.getLanguage();
        String username = currentUser.getUsername();
        session.setAttribute(Attribute.LANGUAGE, language);
        session.setAttribute(Attribute.USERNAME, username);
        session.setAttribute(Attribute.USER_ROLE, userType);
    }

    private String processUserLoginAction(HttpServletRequest request, Optional<User> currentUser,
                                          String username) throws ServiceException {
        String pagePath = PagePath.LOGIN;
        String serverResponse;
        PropertiesReader reader = PropertiesReader.getInstance();
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (currentUser.isPresent()) {
            if (currentUser.get().isActive()) {
                setSessionAttributes(currentUser.get(), request);
                language = (String) session.getAttribute(Attribute.LANGUAGE);
                request.setAttribute(Attribute.LANGUAGE, language);
                pagePath = chooseWorkingPage(request, currentUser.get().getType(), username);
            } else {
                serverResponse = reader.readUserTextProperty(language, Message.USER_NOT_ACTIVE);
                request.setAttribute(Attribute.MESSAGE, serverResponse);
            }
        } else {
            serverResponse = reader.readUserTextProperty(language, Message.WRONG_PASSWORD_OR_USERNAME);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
        }
        return pagePath;
    }

    private String chooseWorkingPage(HttpServletRequest request, UserType type, String username)
            throws ServiceException {
        String pagePath;
        if (type == UserType.ADMIN) {
            pagePath = processAdminData(request);
        } else if (type == UserType.TRAINER) {
            pagePath = processTrainerData(request, username);
        } else {
            pagePath = processUserData(request);
        }
        return pagePath;
    }

    private String processAdminData(HttpServletRequest request) throws ServiceException {
        AdminService adminService = AdminServiceImplementation.getInstance();
        List<User> users = adminService.viewUsers();
        request.setAttribute(Attribute.USERS, users);
        return PagePath.VIEW_USERS;
    }

    private String processUserData(HttpServletRequest request) throws ServiceException {
        MembershipService service = MembershipServiceImplementation.getInstance();
        List<Membership> memberships = service.findMemberships();
        request.setAttribute(Attribute.MEMBERSHIPS, memberships);
        List<User> userTrainers = userService.findAllTrainers();
        request.setAttribute(Attribute.TRAINERS, userTrainers);
        return PagePath.MAIN;
    }

    private String processTrainerData(HttpServletRequest request, String trainerName) throws ServiceException {
        List<User> users = userService.findTrainerUsers(trainerName);
        request.setAttribute(Attribute.USERS, users);
        return PagePath.VIEW_USERS;
    }
}
