package com.anton.web_project.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator instance = new UserValidator();
    private static final String LETTER_NUMBER_REGEX = "[\\w]";
    private static final int MINIMAL_STRING_LENGTH = 6;
    private static final int MAXIMAL_STRING_LENGTH = 29;

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidator() {

    }

    public boolean validateUser(String username, String password) {
        return username != null && password != null &&
                validateStringLength(username) && validateStringLength(password) &&
                validateStringCharacters(username) && validateStringCharacters(password);
    }

    private boolean validateStringLength(String line) {
        return line.length() > MINIMAL_STRING_LENGTH && line.length() < MAXIMAL_STRING_LENGTH;
    }

    private boolean validateStringCharacters(String line) {
        Pattern pattern = Pattern.compile(LETTER_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
