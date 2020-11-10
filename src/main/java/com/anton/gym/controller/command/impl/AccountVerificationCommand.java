package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountVerificationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private AdminService adminService = AdminServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter(Attribute.USERNAME);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse;
        try {
            serverResponse = activateUser(language, username);
        } catch (ServiceException e) {
            LOGGER.warn("Can't unblock user", e);
            serverResponse = reader.readUserTextProperty(language, Message.VERIFICATION_FAILED);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        return PagePath.LOGIN;
    }

    private String activateUser(String language, String username) throws ServiceException {
        String serverResponse = reader.readUserTextProperty(language, Message.VERIFICATION_SUCCEED);
        if (!userService.wasUserMailActivated(username)) {
            boolean wasActivated = true;
            adminService.unblockUser(username);
            userService.updateWasAccountActivated(username, wasActivated);
        } else {
            serverResponse = reader.readUserTextProperty(language, Message.ACCOUNT_ALREADY_ACTIVATED);
        }
        return serverResponse;
    }
}
