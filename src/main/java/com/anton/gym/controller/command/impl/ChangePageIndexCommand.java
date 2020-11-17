package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.controller.command.RequestAttributesWarehouse;
import com.anton.gym.model.entity.User;
import com.anton.gym.util.PaginationIndexesDefiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * The {@code ChangePageIndexCommand} class represents change page index command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ChangePageIndexCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String pageIndexInfo = request.getParameter(Attribute.CURRENT_PAGE_NUMBER);
        HttpSession session = request.getSession();
        session.setAttribute(Attribute.CURRENT_PAGE_NUMBER, pageIndexInfo);
        int pageNumber = PaginationIndexesDefiner.definePageIndex(pageIndexInfo);
        int startingIndex = PaginationIndexesDefiner.defineMinUserIndex(pageNumber);
        fillRequestFromSession(request);
        request.setAttribute(Attribute.STARTING_INDEX, startingIndex);
        List<User> users = (List<User>) request.getAttribute(Attribute.USERS);
        int endingIndex = PaginationIndexesDefiner.defineMaxUserIndex(users.size(), startingIndex);
        request.setAttribute(Attribute.ENDING_INDEX, endingIndex);
        return PagePath.VIEW_USERS;
    }

    private void fillRequestFromSession(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : RequestAttributesWarehouse.getInstance().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
