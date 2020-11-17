package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.creator.MembershipCreator;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code RedactMembershipCommand} class represents redact membership command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class RedactMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private MembershipCreator membershipCreator = MembershipCreator.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = "";
        try {
            Optional<Membership> membership = createMembership(request, session);
            serverResponse = redactMembership(request, membership, language);
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't change data", e);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        return pagePath;
    }

    private String redactMembership(HttpServletRequest request, Optional<Membership> membership, String language)
            throws ServiceException {
        String serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        if (membership.isPresent()) {
            membershipService.update(membership.get());
            fillUserAttributes(request);
        } else {
            fillUserAttributes(request);
            serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
        }
        return serverResponse;
    }

    private void fillUserAttributes(HttpServletRequest request) throws ServiceException {
        List<User> trainers = userService.findAllTrainers();
        List<Membership> memberships = membershipService.findMemberships();
        request.setAttribute(Attribute.TRAINERS, trainers);
        request.setAttribute(Attribute.MEMBERSHIPS, memberships);
    }

    private Optional<Membership> createMembership(HttpServletRequest request, HttpSession session) {
        String name = request.getParameter(Attribute.TITLE);
        String price = request.getParameter(Attribute.PRICE);
        String visits = request.getParameter(Attribute.AMOUNT_OF_VISITS);
        String months = request.getParameter(Attribute.MONTHS);
        String id = (String) session.getAttribute(Attribute.ID);
        String isActive = request.getParameter(Attribute.IS_ACTIVE);
        return membershipCreator.createMembership(id, name, price, visits, months, isActive);
    }
}
