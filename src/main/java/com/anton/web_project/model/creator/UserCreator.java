package com.anton.web_project.model.creator;

import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.util.PasswordEncoder;
import com.anton.web_project.model.validator.UserValidator;

import java.util.Optional;

public class UserCreator {
    public static UserCreator instance = new UserCreator();

    public static UserCreator getInstance() {
        return instance;
    }

    private UserCreator() {}

    public Optional<User> createUser(int id, String username, String password, boolean isActive) {
        UserValidator validator = UserValidator.getInstance();
        Optional<User> result = Optional.empty();
        if(validator.validateUser(username, password)) {
            String encodedPassword = PasswordEncoder.encodeString(password);
            result = Optional.of(new User(id, username, encodedPassword, isActive));
        }
        return result;
    }

    public Optional<User> createUser(String username, String password, boolean isActive) {
        UserValidator validator = UserValidator.getInstance();
        Optional<User> result = Optional.empty();
        if(validator.validateUser(username, password)) {
            String encodedPassword = PasswordEncoder.encodeString(password);
            result = Optional.of(new User(username, encodedPassword, isActive));
        }
        return result;
    }
}
