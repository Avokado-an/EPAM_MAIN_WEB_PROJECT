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
    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImplementation.getInstance();
        HttpSession session = request.getSession();
        String pagePath = PagePath.LOGIN;
        session.removeAttribute(Attribute.USERNAME);
        session.removeAttribute(Attribute.LANGUAGE);
        service.logOut();
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        session.setAttribute(Attribute.USER_ROLE, UserType.GUEST);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return pagePath;
    }
}
