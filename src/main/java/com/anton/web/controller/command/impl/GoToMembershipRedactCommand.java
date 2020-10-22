package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToMembershipRedactCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.REDACT_MEMBERSHIP;
        HttpSession session = request.getSession();
        String id = request.getParameter(Attribute.ID);
        session.setAttribute(Attribute.ID, id);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return pagePath;
    }
}
