package com.anton.gym.controller.command.impl;

import com.anton.gym.controller.command.Attribute;
import com.anton.gym.controller.command.Command;
import com.anton.gym.controller.command.Message;
import com.anton.gym.controller.command.PagePath;
import com.anton.gym.util.PropertiesReader;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.exception.ServiceException;
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

public class RedactUserProfilePictureCommand implements Command {
    private static final UserService userService = UserServiceImplementation.getInstance();
    private static final MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PropertiesReader reader = PropertiesReader.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.USER_PROFILE;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(Attribute.USERNAME);
        String fileName = (String) request.getAttribute(Attribute.PHOTO_REFERENCE);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        try {
            if (userService.updatePhotoReference(username, fileName)) {
                fillRequestAttributes(request, username);
            } else {
                request.setAttribute(
                        Attribute.MESSAGE, reader.readUserTextProperty(language, Message.INVALID_PHOTO_REFERENCE));
            }
        } catch (ServiceException e) {
            String serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            LOGGER.warn("can't change data", e);
        }
        return pagePath;
    }

    private void fillRequestAttributes(HttpServletRequest request, String newUsername) throws ServiceException {
        Optional<Membership> membership = membershipService.findUsersMembership(newUsername);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        List<User> trainers = userService.findUserTrainers(newUsername);
        request.setAttribute(Attribute.TRAINERS, trainers);
        String photoReference = UserFieldsDefiner.defineUserPhotoReference(newUsername);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String description = UserFieldsDefiner.defineUserDescription(newUsername, language);
        request.setAttribute(Attribute.DESCRIPTION, description);
    }
}
