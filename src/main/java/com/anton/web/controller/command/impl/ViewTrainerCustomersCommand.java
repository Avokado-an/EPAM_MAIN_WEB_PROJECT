package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewTrainerCustomersCommand implements Command {
    private UserService userService = UserServiceImplementation.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.VIEW_USERS;
        HttpSession session = request.getSession();
        String trainerName = (String) session.getAttribute(Attribute.USERNAME);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            List<User> users = userService.findTrainerUsers(trainerName);
            request.setAttribute(Attribute.LANGUAGE, language);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't view users", e);
        }
        return pagePath;
    }
}
