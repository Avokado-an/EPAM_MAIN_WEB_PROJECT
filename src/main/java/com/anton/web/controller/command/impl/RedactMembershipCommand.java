package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.RequestAttributesWarehouse;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.creator.MembershipCreator;
import com.anton.web.model.entity.Membership;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.MembershipService;
import com.anton.web.model.service.impl.MembershipServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class RedactMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private MembershipCreator membershipCreator = MembershipCreator.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        HttpSession session = request.getSession();
        String name = request.getParameter(Attribute.TITLE);
        String price = request.getParameter(Attribute.PRICE);
        String visits = request.getParameter(Attribute.AMOUNT_OF_VISITS);
        String months = request.getParameter(Attribute.MONTHS);
        String id = (String) session.getAttribute(Attribute.ID);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        try {
            Optional<Membership> membership = membershipCreator.createMembership(id, name, price, visits, months);
            if (membership.isPresent()) {
                membershipService.update(membership.get());
                List<Membership> memberships = membershipService.findMemberships();
                request.setAttribute(Attribute.LANGUAGE, language);
                request.setAttribute(Attribute.MEMBERSHIPS, memberships);
            } else {
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            }
        } catch (ServiceException e) {
            pagePath = PagePath.ERROR;
            LOGGER.warn("can't change data", e);
        }
        session.setAttribute(Attribute.CURRENT_PAGE, pagePath);
        RequestAttributesWarehouse.getInstance().fillMapWithRequestAttributes(request);
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        request.setAttribute(Attribute.USER_ROLE, session.getAttribute(Attribute.USER_ROLE));
        return pagePath;
    }
}
