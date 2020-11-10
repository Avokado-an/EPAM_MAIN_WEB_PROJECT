package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.*;
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

public class AddMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private MembershipCreator membershipCreator = MembershipCreator.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        HttpSession session = request.getSession();
        String name = request.getParameter(Attribute.TITLE);
        String price = request.getParameter(Attribute.PRICE);
        String visits = request.getParameter(Attribute.AMOUNT_OF_VISITS);
        String months = request.getParameter(Attribute.MONTHS);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        String nextCommand = CommandType.ADD_MEMBERSHIP.name();
        request.setAttribute(Attribute.NEXT_COMMAND, nextCommand);
        try {
            Optional<Membership> membership = membershipCreator.createMembership(name, price, visits, months);
            if (membership.isPresent()) {
                membershipService.addMembership(membership.get());
                List<Membership> memberships = membershipService.findMemberships();
                request.setAttribute(Attribute.MEMBERSHIPS, memberships);
            } else {
                List<User> trainers = userService.findAllTrainers();
                List<Membership> memberships = membershipService.findMemberships();
                request.setAttribute(Attribute.TRAINERS, trainers);
                request.setAttribute(Attribute.MEMBERSHIPS, memberships);
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            }
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't create membership", e);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        return pagePath;
    }
}
