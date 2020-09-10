package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = AdminServiceImplementation.getInstance();
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        String pagePath = ResponsePagePath.VIEW_USERS;
        try {
            boolean wasUserUnblocked = adminService.unblockUser(username);
            if (wasUserUnblocked) {
                request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.OPERATION_SUCCEED);
            } else {
                request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.OPERATION_FAILED);
            }
        } catch (ServiceException e) {
            pagePath = ResponsePagePath.ERROR;
            LOGGER.log(Level.ERROR, "Can't unblock user");
        }
        return pagePath;
    }
}
