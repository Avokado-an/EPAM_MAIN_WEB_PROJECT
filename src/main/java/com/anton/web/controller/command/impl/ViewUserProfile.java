package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.RequestAttributesWarehouse;
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
import java.util.Optional;

public class ViewUserProfile implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MembershipService membershipService = MembershipServiceImplementation.getInstance();
        UserService userService = UserServiceImplementation.getInstance();
        String username = (String) session.getAttribute(Attribute.USERNAME);
        String pagePath = PagePath.USER_PROFILE;
        try {
            List<User> userTrainers = userService.findUserTrainers(username);
            request.setAttribute(Attribute.TRAINERS, userTrainers);
            Optional<Membership> membership = membershipService.findUsersMembership(username);
            membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        } catch (ServiceException e) {
            LOGGER.warn("Can't view user profile", e);
            pagePath = PagePath.ERROR;
        }
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        request.setAttribute(Attribute.USERNAME, username);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return pagePath;
    }
}
