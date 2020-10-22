package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.Attribute;

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
        request.setAttribute(
                Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return (String) request.getAttribute(Attribute.CURRENT_PAGE);
    }

    private void fillRequestFromSession(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : RequestAttributesWarehouse.getInstance().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
