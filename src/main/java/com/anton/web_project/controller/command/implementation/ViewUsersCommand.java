package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.service.AdminService;
import com.anton.web_project.model.service.impl.AdminServiceImplementation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        AdminService service = AdminServiceImplementation.getInstance();
        List<User> users = service.viewUsers();
        String pagePath = ResponsePagePath.VIEW_USERS;
        request.setAttribute(ServletAttribute.USERS, users);
        return pagePath;
    }
}
