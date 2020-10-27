package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.util.PropertiesReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FailedCommand implements Command {
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.ERROR;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = reader.readUserTextProperty(language, Message.PAGE_ERROR_MESSAGE);
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        request.setAttribute(Attribute.LANGUAGE, language);
        return pagePath;
    }
}
