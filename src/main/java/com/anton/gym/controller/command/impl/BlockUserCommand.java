package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
import com.anton.gym.util.ServerResponseDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class BlockUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private AdminService adminService = AdminServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        String username = request.getParameter(Attribute.USERNAME);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse;
        try {
            boolean wasUserBlocked = adminService.blockUser(username);
            serverResponse = ServerResponseDefiner.defineServerResponse(wasUserBlocked, language);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            List<User> users = adminService.viewUsers();
            request.setAttribute(Attribute.LANGUAGE, session.getAttribute(Attribute.LANGUAGE));
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("Can't block user", e);
        }
        return pagePath;
    }
}
