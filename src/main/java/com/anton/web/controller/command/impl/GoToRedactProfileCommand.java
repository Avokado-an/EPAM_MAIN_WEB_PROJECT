package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GoToRedactProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.REDACT_USER_PROFILE;
        HttpSession session = request.getSession();
        String username = request.getParameter(Attribute.USERNAME);
        String description = request.getParameter(Attribute.DESCRIPTION);
        String photoReference = request.getParameter(Attribute.PHOTO_REFERENCE);
        session.setAttribute(Attribute.USERNAME, username);
        session.setAttribute(Attribute.DESCRIPTION, description);
        session.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        return pagePath;
    }
}
