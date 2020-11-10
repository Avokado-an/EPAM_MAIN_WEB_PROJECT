package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MembershipService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.MembershipServiceImplementation;
import com.anton.gym.model.service.impl.UserServiceImplementation;
import com.anton.gym.util.PropertiesReader;
import com.anton.gym.util.UserFieldsDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class RedactUserProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.REDACT_USER_PROFILE;
        HttpSession session = request.getSession();
        String oldName = (String) session.getAttribute(Attribute.USERNAME);
        String newUsername = request.getParameter(Attribute.USERNAME);
        String description = request.getParameter(Attribute.DESCRIPTION);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse;
        try {
            if (updateDescription(request, language, oldName, newUsername, description)) {
                pagePath = PagePath.USER_PROFILE;
                fillRequestAttributes(request, newUsername);
            }
        } catch (ServiceException e) {
            serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            LOGGER.warn("can't change data", e);
        }
        return pagePath;
    }

    private boolean updateDescription
            (HttpServletRequest request, String language, String oldName, String newName, String description)
            throws ServiceException {
        boolean wasUpdated = false;
        if (userService.updateDescription(oldName, description) && userService.updateUsername(oldName, newName)) {
            validRequestParameters(request, newName, description);
            wasUpdated = true;
        } else if (!userService.updateDescription(oldName, description) && userService.updateUsername(oldName, newName)) {
            invalidDescriptionRequestParameters(request, language, newName, oldName);
        } else if (userService.updateDescription(oldName, description) && !userService.updateUsername(oldName, newName)) {
            invalidUsernameRequestParameters(request, language, oldName, description);
        } else {
            invalidUsernameDescriptionRequestParameters(request, language, oldName);
        }
        return wasUpdated;
    }

    private void validRequestParameters(HttpServletRequest request, String newName, String description) {
        request.setAttribute(Attribute.DESCRIPTION, description);
        request.setAttribute(Attribute.USERNAME, newName);
    }

    private void invalidUsernameRequestParameters(
            HttpServletRequest request, String language, String oldName, String description) {
        request.setAttribute(Attribute.USERNAME, oldName);
        request.setAttribute(Attribute.DESCRIPTION, description);
        request.setAttribute(Attribute.MESSAGE, reader.readUserTextProperty(language, Message.INVALID_USERNAME));
    }

    private void invalidUsernameDescriptionRequestParameters(
            HttpServletRequest request, String language, String oldName) throws ServiceException {
        Optional<User> user = userService.findByUsername(oldName);
        user.ifPresent(value -> request.setAttribute(Attribute.DESCRIPTION, value.getDescription()));
        request.setAttribute(Attribute.USERNAME, oldName);
        request.setAttribute(
                Attribute.MESSAGE, reader.readUserTextProperty(language, Message.INVALID_USERNAME_AND_DESCRIPTION));
    }

    private void invalidDescriptionRequestParameters(
            HttpServletRequest request, String language, String newName, String oldName) throws ServiceException {
        Optional<User> user = userService.findByUsername(oldName);
        user.ifPresent(value -> request.setAttribute(Attribute.DESCRIPTION, value.getDescription()));
        request.setAttribute(Attribute.USERNAME, newName);
        request.setAttribute(Attribute.MESSAGE, reader.readUserTextProperty(language, Message.INVALID_DESCRIPTION));
    }

    private void fillRequestAttributes(HttpServletRequest request, String newUsername) throws ServiceException {
        Optional<Membership> membership = membershipService.findUsersMembership(newUsername);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        List<User> trainers = userService.findUserTrainers(newUsername);
        request.setAttribute(Attribute.TRAINERS, trainers);
        String photoReference = UserFieldsDefiner.defineUserPhotoReference(newUsername);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
    }
}
