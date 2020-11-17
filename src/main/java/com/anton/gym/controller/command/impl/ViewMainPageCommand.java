package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The {@code ViewMailPageCommand} class represents view main page command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ViewMainPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        try {
            List<Membership> memberships = findMemberships(request);
            List<User> trainers = userService.findAllTrainers();
            request.setAttribute(Attribute.TRAINERS, trainers);
            request.setAttribute(Attribute.MEMBERSHIPS, memberships);
        } catch (ServiceException e) {
            LOGGER.warn("can't view main page", e);
            pagePath = PagePath.ERROR;
        }
        return pagePath;
    }

    private List<Membership> findMemberships(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        UserType currentRole = (UserType) session.getAttribute(Attribute.USER_ROLE);
        List<Membership> memberships;
        if (currentRole == UserType.ADMIN) {
            memberships = membershipService.findMemberships();
        } else {
            memberships = membershipService.findActiveMemberships();
        }
        return memberships;
    }
}
