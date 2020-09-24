package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.anton.web_project.controller.response.ServletAttribute.LANGUAGE_ATTRIBUTE;
import static com.anton.web_project.controller.response.ServletAttribute.USERS;

public class ViewUsersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        AdminService service = AdminServiceImplementation.getInstance();
        String pagePath = ResponsePagePath.VIEW_USERS;
        try {
            List<User> users = service.viewUsers();
            HttpSession session = request.getSession();
            request.setAttribute(LANGUAGE_ATTRIBUTE, session.getAttribute(LANGUAGE_ATTRIBUTE));
            request.setAttribute(USERS, users);
        } catch (ServiceException e) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.ERROR, "can't view ssers");
        }
        return pagePath;
    }
}
