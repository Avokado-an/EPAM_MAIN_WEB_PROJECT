package com.anton.web.model.creator;

import com.anton.web.model.entity.User;
import com.anton.web.model.entity.UserType;
import com.anton.web.model.validator.UserValidator;

import java.util.Optional;

public class UserCreator {
    public static UserCreator instance = new UserCreator();

    public static UserCreator getInstance() {
        return instance;
    }

    private UserCreator() {
    }

    public Optional<User> createUser(
            int id, String username, String email, String userType, boolean isActive, String language,
            String description, String photoReference) {
        UserValidator validator = UserValidator.getInstance();
        Optional<User> result = Optional.empty();
        UserType type = UserType.valueOf(userType.toUpperCase());
        if (validator.validateUser(username, email)) {
            result = Optional.of(new User(id, username, email, type, isActive, language, photoReference, description));
        }
        return result;
    }

    public Optional<User> createUser(String username, String email, boolean isActive) {
        UserValidator validator = UserValidator.getInstance();
        Optional<User> result = Optional.empty();
        if (validator.validateUser(username, email)) {
            result = Optional.of(new User(username, email, isActive));
        }
        return result;
    }

    public Optional<User> createUser(String username, String description, String photoReference) {
        UserValidator validator = UserValidator.getInstance();
        Optional<User> result = Optional.empty();
        //if (validator.validateUser(username)) {
            result = Optional.of(new User(username, description, photoReference));
        //}
        return result;
    }
}