package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;
import com.anton.web_project.controller.response.ServletAttribute;
import com.anton.web_project.controller.response.ServletMessage;

import javax.servlet.http.HttpServletRequest;

public class FailedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ResponsePagePath.ERROR;
        request.setAttribute(ServletAttribute.MESSAGE_ATTRIBUTE, ServletMessage.ERROR_MESSAGE);
        return page;
    }
}
