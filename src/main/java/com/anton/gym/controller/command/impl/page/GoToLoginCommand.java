package com.anton.gym.controller.command.impl.page;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The {@code GoToLoginCommand} class represents go to login command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class GoToLoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.LOGIN;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        return pagePath;
    }
}
