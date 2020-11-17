package com.anton.gym.controller.command.impl.page;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.CommandType;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * The {@code GoToRedactMembershipCommand} class represents go to redact membership command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class GoToRedactMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.REDACT_MEMBERSHIP;
        HttpSession session = request.getSession();
        String id = request.getParameter(Attribute.ID);
        session.setAttribute(Attribute.ID, id);
        String nextCommand = CommandType.REDACT_MEMBERSHIP.name();
        request.setAttribute(Attribute.NEXT_COMMAND, nextCommand);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        request.setAttribute(Attribute.LANGUAGE, language);
        int numberId = Integer.parseInt(id);
        try {
            fillOriginalMembershipRequestParameters(numberId, request);
        } catch (ServiceException e) {
            LOGGER.error("cant go to redact membership page: ", e);
            pagePath = PagePath.ERROR;
        }
        return pagePath;
    }

    private void fillOriginalMembershipRequestParameters(int id, HttpServletRequest request) throws ServiceException {
        Optional<Membership> membership = membershipService.find(id);
        if (membership.isPresent()) {
            request.setAttribute(Attribute.TITLE, membership.get().getName());
            request.setAttribute(Attribute.MONTHS, membership.get().getMonths());
            request.setAttribute(Attribute.AMOUNT_OF_VISITS, membership.get().getAmountOfAttendees());
            request.setAttribute(Attribute.PRICE, membership.get().getPrice());
            request.setAttribute(Attribute.IS_ACTIVE, membership.get().isActive());
        }
    }
}
