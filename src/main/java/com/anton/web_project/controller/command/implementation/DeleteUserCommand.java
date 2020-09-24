package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.anton.web_project.controller.response.ServletAttribute.*;
import static com.anton.web_project.controller.response.ServletMessage.OPERATION_SUCCEED;

public class DeleteUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = AdminServiceImplementation.getInstance();
        String pagePath = ResponsePagePath.VIEW_USERS;
        try {
            int id = Integer.parseInt(request.getParameter(ID_ATTRIBUTE));
            adminService.deleteUserById(id);
            HttpSession session = request.getSession();
            request.setAttribute(LANGUAGE_ATTRIBUTE, session.getAttribute(LANGUAGE_ATTRIBUTE));
            request.setAttribute(MESSAGE_ATTRIBUTE, OPERATION_SUCCEED);
        } catch (ServiceException e) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.ERROR, "can't delete user");
        }
        return pagePath;
    }
}
