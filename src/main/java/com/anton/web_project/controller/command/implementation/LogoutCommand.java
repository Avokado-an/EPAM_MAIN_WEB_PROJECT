package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.model.service.UserService;
import com.anton.web_project.model.service.impl.UserServiceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImplementation.getInstance();
        HttpSession session = request.getSession();
        session.removeAttribute(ServletAttribute.USERNAME_ATTRIBUTE);
        service.logOut();
        return ResponsePagePath.LOGIN;
    }
}
