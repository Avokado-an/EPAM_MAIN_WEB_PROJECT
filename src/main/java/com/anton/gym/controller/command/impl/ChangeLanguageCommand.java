package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.RequestAttributesWarehouse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String language = request.getParameter(Attribute.LANGUAGE);
        session.setAttribute(Attribute.LANGUAGE, language);
        String page = (String) session.getAttribute(Attribute.CURRENT_PAGE);
        fillRequestFromSession(request);
        request.setAttribute(Attribute.LANGUAGE, language);
        if(page != null) {
            request.setAttribute(Attribute.CURRENT_PAGE, page);
        }
        return (String) request.getAttribute(Attribute.CURRENT_PAGE);
    }

    private void fillRequestFromSession(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : RequestAttributesWarehouse.getInstance().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
