package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.MoneyAccountService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.MoneyAccountServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.PropertiesReader;
import com.anton.gym.util.UsersOnPageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The {@code LoginCommand} class represents login command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MoneyAccountService moneyAccountService = MoneyAccountServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath;
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);
        try {
            Optional<User> currentUser = userService.logIn(username, password);
            pagePath = processUserLoginAction(request, currentUser);
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

    private String processUserLoginAction(HttpServletRequest request, Optional<User> currentUser)
            throws ServiceException {
        String pagePath = PagePath.LOGIN;
        String serverResponse;
        PropertiesReader reader = PropertiesReader.getInstance();
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        if (currentUser.isPresent()) {
            if (currentUser.get().isActive()) {
                setSessionAttributes(currentUser.get(), request);
                pagePath = chooseWorkingPage(request, currentUser.get().getType());
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

    private String chooseWorkingPage(HttpServletRequest request, UserType type)
            throws ServiceException {
        String pagePath;
        if (type == UserType.ADMIN) {
            pagePath = processAdminData(request);
        } else if (type == UserType.TRAINER) {
            pagePath = processTrainerData(request);
        } else {
            pagePath = processUserData(request);
        }
        return pagePath;
    }

    private String processAdminData(HttpServletRequest request) throws ServiceException {
        List<User> users = UsersOnPageHandler.viewUsersOnPage(request);
        request.setAttribute(Attribute.USERS, users);
        return PagePath.VIEW_USERS;
    }

    private String processUserData(HttpServletRequest request) throws ServiceException {
        MembershipService service = MembershipServiceImplementation.getInstance();
        List<Membership> memberships = service.findActiveMemberships();
        request.setAttribute(Attribute.MEMBERSHIPS, memberships);
        List<User> userTrainers = userService.findAllTrainers();
        request.setAttribute(Attribute.TRAINERS, userTrainers);
        representBalance(request);
        return PagePath.MAIN;
    }

    private String processTrainerData(HttpServletRequest request) throws ServiceException {
        List<User> users = UsersOnPageHandler.viewUsersOnPage(request);
        request.setAttribute(Attribute.USERS, users);
        return PagePath.VIEW_USERS;
    }

    private void representBalance(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserType currentRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        if (currentRole == UserType.CLIENT) {
            String username = (String) session.getAttribute(Attribute.USERNAME);
            try {
                BigDecimal currentBalance = moneyAccountService.checkMoneyOnAccount(username);
                session.setAttribute(Attribute.BALANCE, currentBalance);
            } catch (ServiceException e) {
                int zero = 0;
                session.setAttribute(Attribute.BALANCE, zero);
            }
        }
    }
}
