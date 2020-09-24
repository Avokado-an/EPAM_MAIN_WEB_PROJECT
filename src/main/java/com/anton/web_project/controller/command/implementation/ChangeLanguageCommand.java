package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.anton.web_project.controller.response.ServletAttribute.*;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String language = request.getParameter(LANGUAGE_ATTRIBUTE);
        String pagePath = request.getParameter(CURRENT_PAGE_ATTRIBUTE);
        session.setAttribute(LANGUAGE_ATTRIBUTE, language);
        return pagePath;
    }
}
