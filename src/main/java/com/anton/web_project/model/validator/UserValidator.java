package com.anton.web_project.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator instance = new UserValidator();
    private static final String LETTER_NUMBER_REGEX = "[\\w]{6,29}"; //todo refactor specially for different fields and stuff

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidator() {

    } //todo create table names(id int auto_increment, ...)

    public boolean validateUser(String username, String password) {
        return username != null && password != null &&
                validateStringCharacters(username) && validateStringCharacters(password);
    }

    private boolean validateStringCharacters(String line) {
        Pattern pattern = Pattern.compile(LETTER_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
