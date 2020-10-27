package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;
import com.anton.web.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private AdminService service = AdminServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            List<User> users = service.viewUsers();
            request.setAttribute(Attribute.LANGUAGE, language);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't view users", e);
        }
        return pagePath;
    }
}
