package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        AdminService adminService = AdminServiceImplementation.getInstance();
        String username = request.getParameter(ServletAttribute.USERNAME_ATTRIBUTE);
        boolean wasUserBlocked = adminService.blockUser(username);
        if (wasUserBlocked) {
            request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.OPERATION_SUCCEED);
        } else {
            request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.OPERATION_FAILED);
        }
        return ResponsePagePath.VIEW_USERS;
    }
}
