package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
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

public class UnblockUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = AdminServiceImplementation.getInstance();
        String username = request.getParameter(Attribute.USERNAME);
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        PropertiesReader reader = PropertiesReader.getInstance();
        String serverResponse;
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            boolean wasUserUnblocked = adminService.unblockUser(username);
            if (wasUserUnblocked) {
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
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return pagePath;
    }
}
