package com.anton.gym.util;

import com.anton.gym.controller.command.Message;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.UserServiceImplementation;

import java.util.Optional;

/**
 * The {@code UserFieldsDefiner} class represents UserFieldsDefiner.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class UserFieldsDefiner {
    private static UserService userService = UserServiceImplementation.getInstance();
    private static final PropertiesReader reader = PropertiesReader.getInstance();

    /**
     * define the user description
     * @param username the name of the user to find
     * @param language the language
     * @return the description
     * @throws ServiceException
     */
    public static String defineUserDescription(String username, String language) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String description = reader.readUserTextProperty(language, Message.ABOUT_ME);
        if (user.isPresent()) {
            if (user.get().getDescription() != null) {
                if (!user.get().getDescription().isEmpty()) {
                    description = user.get().getDescription();
                }
            }
        }
        return description;
    }

    /**
     * define the photo reference of chosen user
     * @param username the name of the user to find
     * @return the photo path
     * @throws ServiceException
     */
    public static String defineUserPhotoReference(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String photoReference = "default_picture.png";
        if (user.isPresent()) {
            if (user.get().getPhotoReference() != null) {
                photoReference = user.get().getPhotoReference();
            }
        }
        return photoReference;
    }

    /**
     * define the language of the user
     * @param username the name of the user to find
     * @return the users language
     * @throws ServiceException
     */
    public static String defineUserLanguage(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String language = "en_Us";
        if (user.isPresent()) {
            language = user.get().getLanguage();
        }
        return language;
    }

    /**
     * define the user email
     * @param trainerName the name of the user to find
     * @return the email of chosen user
     * @throws ServiceException
     */
    public static String defineUserMail(String trainerName) throws ServiceException {
        Optional<User> trainer = userService.findByUsername(trainerName);
        String email = "";
        if (trainer.isPresent()) {
            email = trainer.get().getEmail();
        }
        return email;
    }
}
