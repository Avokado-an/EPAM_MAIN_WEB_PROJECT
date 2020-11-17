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
import com.anton.gym.util.ServerResponseDefiner;
import com.anton.gym.util.UserFieldsDefiner;
import com.anton.gym.util.mail.MailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The {@code SignForCoachCommand} class represents sign for coach command.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class SignForCoachCommand implements Command {
    private static final String SPACE = " ";
    private static final boolean IS_PROFILE_OWNER = true;
    private static final Logger LOGGER = LogManager.getLogger();
    private UserService userService = UserServiceImplementation.getInstance();
    private MembershipService membershipService = MembershipServiceImplementation.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String pagePath = PagePath.USER_PROFILE;
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute(Attribute.LANGUAGE);
        String trainerName = request.getParameter(Attribute.USERNAME);
        String username = (String) session.getAttribute(Attribute.USERNAME);
        try {
            boolean wasTrainerIdUpdated = userService.updateTrainerId(username, trainerName);
            if (wasTrainerIdUpdated) {
                sendMessage(trainerName, username);
            }
            String serverResponse = ServerResponseDefiner.defineServerResponse(wasTrainerIdUpdated, language);
            fillUserAttributes(request, username, language);
            request.setAttribute(Attribute.MESSAGE, serverResponse);
        } catch (ServiceException e) {
            LOGGER.warn("can't view users", e);
        }
        request.setAttribute(Attribute.USERNAME, username);
        return pagePath;
    }

    private void fillUserAttributes(HttpServletRequest request, String username, String language)
            throws ServiceException {
        HttpSession session = request.getSession();
        session.setAttribute(Attribute.IS_PROFILE_OWNER, IS_PROFILE_OWNER);
        String description = UserFieldsDefiner.defineUserDescription(username, language);
        String photoReference = UserFieldsDefiner.defineUserPhotoReference(username);
        request.setAttribute(Attribute.DESCRIPTION, description);
        request.setAttribute(Attribute.PHOTO_REFERENCE, photoReference);
        Optional<Membership> membership = membershipService.findUsersMembership(username);
        membership.ifPresent(m -> request.setAttribute(Attribute.MEMBERSHIP, m));
        List<User> trainers = userService.findUserTrainers(username);//todo make 1 trainer for 1 user
        request.setAttribute(Attribute.TRAINERS, trainers);
    }

    private void sendMessage(String trainerName, String username) throws ServiceException {
        String trainerEmail = UserFieldsDefiner.defineUserMail(trainerName);
        String userEmail = UserFieldsDefiner.defineUserMail(username);
        String language = UserFieldsDefiner.defineUserLanguage(trainerName);
        MailSender sender = send(language, trainerEmail, username, userEmail);
        sender.send();
    }

    private MailSender send(String language, String email, String username, String userEmail) {
        PropertiesReader reader = PropertiesReader.getInstance();
        String emailSubject = reader.readUserTextProperty(language, Message.NEW_CLIENT_EMAIL_SUBJECT);
        StringBuilder message = new StringBuilder();
        message.append(SPACE).append(username).append(SPACE).append(userEmail);
        return new MailSender(email, emailSubject,
                message.toString());
    }
}
