package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.util.PropertiesReader;

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
