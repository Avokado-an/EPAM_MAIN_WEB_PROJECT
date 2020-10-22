package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;
import com.anton.web.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteUserCommand implements Command {//todo do I even need this(95% no)
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = AdminServiceImplementation.getInstance();
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(request.getParameter(Attribute.ID));
            adminService.deleteUserById(id);
            request.setAttribute(Attribute.LANGUAGE, session.getAttribute(Attribute.LANGUAGE));
            request.setAttribute(Attribute.MESSAGE, Message.OPERATION_SUCCEED);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.log(Level.ERROR, "can't delete user", e);
        }
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        return pagePath;
    }
}
