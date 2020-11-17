package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.UserFieldsDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code ViewUserProfileCommand} class represents view user profile command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class ViewUserProfile implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private UserService userService = UserServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = defineUsername(request);
        String pagePath = PagePath.USER_PROFILE;
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        boolean isViewerProfileOwner = isViewerProfileOwner(request);
        session.setAttribute(Attribute.IS_PROFILE_OWNER, isViewerProfileOwner);
        try {
            fillUserAttributes(request, username, language);
        } catch (ServiceException e) {
            LOGGER.warn("Can't view user profile", e);
            pagePath = PagePath.ERROR;
        }
        request.setAttribute(Attribute.USERNAME, username);
        return pagePath;
    }

    private void fillUserAttributes(HttpServletRequest request, String username, String language)
            throws ServiceException {
        String description = UserFieldsDefiner.defineUserDescription(username, language);
        String photoReference = UserFieldsDefiner.defineUserPhotoReference(username);
        request.setAttribute(Attribute.DESCRIPTION, description);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
        List<User> userTrainers = userService.findUserTrainers(username);
        request.setAttribute(Attribute.TRAINERS, userTrainers);
        Optional<Membership> membership = membershipService.findUsersMembership(username);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
    }

    private String defineUsername(HttpServletRequest request) {
        String username = request.getParameter(Attribute.USERNAME);
        if (username == null || username.isEmpty()) {
            HttpSession session = request.getSession();
            username = (String) session.getAttribute(Attribute.USERNAME);
        }
        return username;
    }

    private boolean isViewerProfileOwner(HttpServletRequest request) {
        boolean isProfileOwner = false;
        String username = request.getParameter(Attribute.USERNAME);
        if (username == null || username.isEmpty()) {
            isProfileOwner = true;
        }
        return isProfileOwner;
    }
}
