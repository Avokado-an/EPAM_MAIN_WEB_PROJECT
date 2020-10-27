package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;
import com.anton.web.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MarkTrainerAsUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int CLIENT_POSITION_ID = 1;
    private AdminService adminService = AdminServiceImplementation.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(Attribute.USERNAME);
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        String serverResponse;
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            boolean wasUserMarked = adminService.changeUserPosition(username, CLIENT_POSITION_ID);
            if (wasUserMarked) {
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
            } else {
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            }
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            List<User> users = adminService.viewUsers();
            request.setAttribute(Attribute.LANGUAGE, language);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("Can't unblock user", e);
        }
        return pagePath;
    }
}
