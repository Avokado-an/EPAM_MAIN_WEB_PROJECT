package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.Attribute;
import com.anton.web.controller.command.Command;
import com.anton.web.controller.command.Message;
import com.anton.web.controller.command.PagePath;
import com.anton.web.controller.util.PropertiesReader;
import com.anton.web.model.entity.Membership;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.MembershipService;
import com.anton.web.model.service.UserService;
import com.anton.web.model.service.impl.MembershipServiceImplementation;
import com.anton.web.model.service.impl.UserServiceImplementation;
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
        String pagePath = PagePath.USER_PROFILE;
        HttpSession session = request.getSession();
        String oldName = (String) session.getAttribute(Attribute.USERNAME);
        String newUsername = request.getParameter(Attribute.USERNAME);
        String description = request.getParameter(Attribute.DESCRIPTION);
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
        try {
            userService.updateDescription(oldName, description);
            request.setAttribute(Attribute.DESCRIPTION, description);
            userService.updateUsername(oldName, newUsername);
            request.setAttribute(Attribute.USERNAME, newUsername);
            session.setAttribute(Attribute.USERNAME, newUsername);
            fillRequestAttributes(request, newUsername);
        } catch (ServiceException e) {
            serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            LOGGER.warn("can't change data", e);
        }
        request.setAttribute(Attribute.MESSAGE, serverResponse);
        return pagePath;
    }

    private void fillRequestAttributes(HttpServletRequest request, String newUsername) throws ServiceException {
        Optional<Membership> membership = membershipService.findUsersMembership(newUsername);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        List<User> trainers = userService.findUserTrainers(newUsername);
        request.setAttribute(Attribute.TRAINERS, trainers);
        String photoReference = defineUserPhotoReference(newUsername);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
    }

    private String defineUserPhotoReference(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String photoReference = "img/photo/default_picture.png";
        if (user.isPresent()) {
            if (user.get().getPhotoReference() != null) {
                photoReference = user.get().getPhotoReference();
            }
        }
        return photoReference;
    }

    private String formPhotoReference(String photoReference) {
        return "img/photo/" + photoReference;
    }
}
