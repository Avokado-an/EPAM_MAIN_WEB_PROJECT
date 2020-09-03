package com.anton.web_project.model.validator;

public class UserValidator {
    private static UserValidator instance = new UserValidator();

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidator() {

    }

    public boolean validateUser(String username, String password) {
        boolean flag = false;
        if (username != null && password != null &&
                validateStringLength(username) && validateStringLength(password)) {
            flag = true;
        }
        return flag;//add extra logic and stuff
    }

    private boolean validateStringLength(String line) {
        return line.length() > 4;
    }
}
