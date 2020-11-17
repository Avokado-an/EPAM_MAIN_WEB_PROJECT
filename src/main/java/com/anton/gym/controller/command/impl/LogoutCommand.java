package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.controller.command.RequestAttributesWarehouse;
import com.anton.gym.model.entity.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code LogoutCommand} class represents logout command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pagePath = PagePath.LOGIN;
        session.removeAttribute(Attribute.USERNAME);
        session.removeAttribute(Attribute.LANGUAGE);
        session.removeAttribute(Attribute.BALANCE);
        RequestAttributesWarehouse.getInstance().clear();
        session.setAttribute(Attribute.USER_ROLE, UserType.GUEST);
        return pagePath;
    }
}
