package com.anton.web_project.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator instance = new UserValidator();
    private static final String LETTER_NUMBER_REGEX = "[\\w]{4,29}"; //todo refactor specially for different fields and stuff
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidator() {

    } //todo create table names(id int auto_increment, ...)

    public boolean validateUser(String username, String password, String email) {
        return username != null && validateStringCharacters(username) &&
                password != null && validateStringCharacters(password) &&
                email != null && validateEmail(email);
    }

    public boolean validateUser(String username, String email) {
        return username != null && validateStringCharacters(username) &&
                email != null && validateEmail(email);
    }

    private boolean validateStringCharacters(String line) {
        Pattern pattern = Pattern.compile(LETTER_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
