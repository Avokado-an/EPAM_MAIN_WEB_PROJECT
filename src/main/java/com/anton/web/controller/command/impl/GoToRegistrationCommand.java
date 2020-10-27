package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.REGISTRATION;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        return pagePath;
    }
}
