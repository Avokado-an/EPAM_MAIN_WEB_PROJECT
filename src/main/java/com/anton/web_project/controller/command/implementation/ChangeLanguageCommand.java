package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ServletAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String language = request.getParameter(ServletAttribute.LANGUAGE_ATTRIBUTE);
        String pagePath = request.getParameter(ServletAttribute.CURRENT_PAGE_ATTRIBUTE);
        session.setAttribute(ServletAttribute.LANGUAGE_ATTRIBUTE, language);
        return pagePath;
    }
}
