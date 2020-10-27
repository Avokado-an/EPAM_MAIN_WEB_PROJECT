package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.model.entity.UserType;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.UserServiceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private UserService service = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pagePath = PagePath.LOGIN;
        session.removeAttribute(Attribute.USERNAME);
        session.removeAttribute(Attribute.LANGUAGE);
        service.logOut();
        session.setAttribute(Attribute.USER_ROLE, UserType.GUEST);
        return pagePath;
    }
}
