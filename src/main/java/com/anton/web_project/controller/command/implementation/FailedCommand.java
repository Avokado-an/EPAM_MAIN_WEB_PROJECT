package com.anton.web_project.controller.command.implementation;

import com.anton.web_project.controller.command.Command;
import com.anton.web_project.controller.response.ResponsePagePath;

import javax.servlet.http.HttpServletRequest;

import static com.anton.web_project.controller.response.ServletAttribute.*;
import static com.anton.web_project.controller.response.ServletMessage.ERROR_MESSAGE;

public class FailedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ResponsePagePath.ERROR;
        request.setAttribute(MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
        request.setAttribute(LANGUAGE_ATTRIBUTE, DEFAULT_ENGLISH_LANGUAGE);
        return page;
    }
}
