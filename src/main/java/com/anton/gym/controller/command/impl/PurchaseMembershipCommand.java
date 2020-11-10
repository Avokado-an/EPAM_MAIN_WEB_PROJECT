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
import java.util.List;
import java.util.Optional;

public class PurchaseMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.USER_PROFILE;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String membershipId = request.getParameter(Attribute.ID);
        String username = (String) session.getAttribute(Attribute.USERNAME);
        String serverResponse;
        try {
            boolean wasUserUpdated = userService.updateMembershipId(username, membershipId);
            serverResponse = ServerResponseDefiner.defineServerResponse(wasUserUpdated, language);
            String description = UserFieldsDefiner.defineUserDescription(username, language);
            String photoReference = UserFieldsDefiner.defineUserPhotoReference(username);
            request.setAttribute(Attribute.DESCRIPTION, description);
            request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
            Optional<Membership> membership = membershipService.findUsersMembership(username);
            membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
            List<User> trainers = userService.findUserTrainers(username);
            request.setAttribute(Attribute.TRAINERS, trainers);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
        } catch (ServiceException | TransactionException e) {
            LOGGER.warn("can't view users", e);
        }
        request.setAttribute(Attribute.USERNAME, username);
        return pagePath;
    }
}
