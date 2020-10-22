package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;
import com.anton.web.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountVerificationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Attribute.USERNAME);
        AdminService adminService = AdminServiceImplementation.getInstance();
        PropertiesReader reader = PropertiesReader.getInstance();
        String pagePath = PagePath.LOGIN;
        String serverResponse;
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            serverResponse = reader.readUserTextProperty(language, Message.VERIFICATION_SUCCEED);
            adminService.unblockUser(username);
        } catch (ServiceException e) {
            LOGGER.warn("Can't unblock user", e);
            serverResponse = reader.readUserTextProperty(language, Message.VERIFICATION_FAILED);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        request.setAttribute(Attribute.LANGUAGE, Attribute.DEFAULT_ENGLISH_LANGUAGE);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        return pagePath;
    }
}
