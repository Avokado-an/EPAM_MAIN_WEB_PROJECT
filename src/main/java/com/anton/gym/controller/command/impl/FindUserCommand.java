package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();
    private AdminService adminService = AdminServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.VIEW_USERS;
        String nameToFind = request.getParameter(Attribute.USERNAME);
        try {
            List<User> users = findUsers(request, nameToFind);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't view users", e);
        }
        return pagePath;
    }

    private List<User> findUsers(HttpServletRequest request, String username) throws ServiceException {
        HttpSession session = request.getSession();
        UserType userRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        List<User> usersToFind = new ArrayList<>();
        if(userRole == UserType.ADMIN) {
            usersToFind = adminService.findUsersByUsername(username);
        } else if(userRole == UserType.TRAINER) {
            usersToFind = findTrainerUserByName(request, username);
        }
        return usersToFind;
    }

    private List<User> findTrainerUserByName(HttpServletRequest request, String username) throws ServiceException {
        HttpSession session = request.getSession();
        String trainerName = (String) session.getAttribute(Attribute.USERNAME);
        return userService.findTrainerUserByUsername(trainerName, username);
    }
}