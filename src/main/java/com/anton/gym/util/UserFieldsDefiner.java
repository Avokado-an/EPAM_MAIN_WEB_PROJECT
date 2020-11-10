package com.anton.gym.util;

import com.anton.gym.controller.command.Message;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.service.impl.UserServiceImplementation;

import java.util.Optional;

public class UserFieldsDefiner {
    private static UserService userService = UserServiceImplementation.getInstance();
    private static final PropertiesReader reader = PropertiesReader.getInstance();

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

    public static String defineUserLanguage(String username) throws ServiceException {
        Optional<User> user = userService.findByUsername(username);
        String language = "en_Us";
        if (user.isPresent()) {
            language = user.get().getLanguage();
        }
        return language;
    }

    public static String defineUserMail(String trainerName) throws ServiceException {
        Optional<User> trainer = userService.findByUsername(trainerName);
        String email = "";
        if (trainer.isPresent()) {
            email = trainer.get().getEmail();
        }
        return email;
    }
}
