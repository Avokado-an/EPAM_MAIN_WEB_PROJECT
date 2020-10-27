package com.anton.web.controller.command.impl;

import com.anton.web.controller.command.*;
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
import java.util.Optional;

public class PurchaseMembershipCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();
    private PropertiesReader reader = PropertiesReader.getInstance();

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
            if (wasUserUpdated) {
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_SUCCEED);
            } else {
                serverResponse = reader.readUserTextProperty(language, Message.OPERATION_FAILED);
            }
            String description = defineUserDescription(username);
            String photoReference = defineUserPhotoReference(username);
            request.setAttribute(Attribute.DESCRIPTION, description);
            request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
            Optional<Membership> membership = membershipService.findUsersMembership(username);
            membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
            request.setAttribute(Attribute.MESSAGE, serverResponse);
            request.setAttribute(Attribute.LANGUAGE, language);
        } catch (ServiceException e) {
            LOGGER.warn("can't view users", e);
        }
        request.setAttribute(Attribute.USERNAME, username);
        return pagePath;
    }

    private String defineUserDescription(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String description = "";
        if(user.isPresent()) {
            description = user.get().getDescription();
        }
        return description;
    }

    private String defineUserPhotoReference(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String photoReference = "";
        if(user.isPresent()) {
            photoReference = user.get().getPhotoReference();
        }
        return photoReference;
    }
}
