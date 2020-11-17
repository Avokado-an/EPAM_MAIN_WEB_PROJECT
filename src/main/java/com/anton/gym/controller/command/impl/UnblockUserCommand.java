package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
import com.anton.gym.util.ServerResponseDefiner;
import com.anton.gym.util.UsersOnPageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code UnblockUserCommand} class represents unblock user command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class UnblockUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private AdminService adminService = AdminServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(Attribute.USERNAME);
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        String serverResponse;
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            boolean wasUserUnblocked = adminService.unblockUser(username);
            serverResponse = ServerResponseDefiner.defineServerResponse(wasUserUnblocked, language);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            List<User> users = UsersOnPageHandler.viewUsersOnPage(request);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("Can't unblock user", e);
        }
        return pagePath;
    }
}
