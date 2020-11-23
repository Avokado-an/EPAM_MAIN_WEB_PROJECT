package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.exception.TransactionException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.ServerResponseDefiner;
import com.anton.gym.util.UserFieldsDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The {@code PurchaseMembershipCommand} class represents purchase membership command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class PurchaseMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final boolean PROFILE_OWNER = true;
    private UserService userService = UserServiceImplementation.getInstance();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.MAIN;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String username = (String) session.getAttribute(Attribute.USERNAME);
        String serverResponse;
        try {
            boolean wasUserUpdated = updateUserMembership(request, username);
            Optional<Membership> membership = membershipService.findUsersMembership(username);
            serverResponse = ServerResponseDefiner.defineServerResponse(wasUserUpdated, language);
            fillUserAttributes(request, username, membership, language);
            updateBalance(wasUserUpdated, membership, session);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            session.setAttribute(Attribute.IS_PROFILE_OWNER, PROFILE_OWNER);
        } catch (ServiceException | TransactionException e) {
            LOGGER.warn("can't view users", e);
        }
        request.setAttribute(Attribute.USERNAME, username);
        return pagePath;
    }

    private void fillUserAttributes(HttpServletRequest request, String username,
                                    Optional<Membership> membership, String language) throws ServiceException {
        String description = UserFieldsDefiner.defineUserDescription(username, language);
        String photoReference = UserFieldsDefiner.defineUserPhotoReference(username);
        request.setAttribute(Attribute.DESCRIPTION, description);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        List<User> trainers = userService.findUserTrainers(username);
        request.setAttribute(Attribute.TRAINERS, trainers);
    }

    private void updateBalance(boolean wasUserUpdated, Optional<Membership> membership, HttpSession session) {
        if (wasUserUpdated) {
            membership.ifPresent(m -> changeSessionBalance(session, m.getPrice()));
        }
    }

    private boolean updateUserMembership(HttpServletRequest request, String username)
            throws ServiceException, TransactionException {
        String membershipId = request.getParameter(Attribute.ID);
        return userService.updateMembershipId(username, membershipId);
    }

    private void changeSessionBalance(HttpSession session, double membershipPrice) {
        BigDecimal currentBalance = (BigDecimal) session.getAttribute(Attribute.BALANCE);
        List<BigDecimal> priceToPay = Collections.singletonList(BigDecimal.valueOf(membershipPrice * -1));
        currentBalance = priceToPay.stream().reduce(currentBalance, BigDecimal::add);
        session.setAttribute(Attribute.BALANCE, currentBalance);
    }
}
