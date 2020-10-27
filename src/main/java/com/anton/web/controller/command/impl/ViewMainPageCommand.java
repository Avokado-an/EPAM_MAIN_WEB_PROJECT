package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;
import com.anton.web.model.entity.Membership;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.MembershipService;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.MembershipServiceImplementation;
import com.anton.web.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewMainPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            List<Membership> memberships = membershipService.findMemberships();
            List<User> trainers = userService.findAllTrainers();
            request.setAttribute(Attribute.TRAINERS, trainers);
            request.setAttribute(Attribute.LANGUAGE, language);
            request.setAttribute(Attribute.MEMBERSHIPS, memberships);
        } catch (ServiceException e) {
            LOGGER.warn("can't view main page", e);
            pagePath = PagePath.ERROR;
        }
        return pagePath;
    }
}
