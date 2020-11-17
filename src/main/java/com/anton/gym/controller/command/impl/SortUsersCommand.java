package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.util.UsersOnPageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The {@code SortUsersCommand} class represents sort users command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class SortUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.VIEW_USERS;
        String fieldToCompare = request.getParameter(Attribute.FIELD_TO_COMPARE);
        try {
            List<User> users = UsersOnPageHandler.viewSortedUsersOnPage(request, fieldToCompare);
            request.setAttribute(Attribute.USERS, users);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't view users", e);
        }
        return pagePath;
    }
}
