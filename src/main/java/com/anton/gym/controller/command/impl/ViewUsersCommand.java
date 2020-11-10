package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.controller.command.Attribute;
import com.anton.gym.model.entity.User;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.service.AdminService;
import com.anton.gym.model.service.impl.AdminServiceImplementation;
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
        try {
            List<User> users = service.viewUsers();
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't view users", e);
        }
        return pagePath;
    }
}
