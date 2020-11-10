package com.anton.gym.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static UserValidator instance = new UserValidator();
    private static final String NON_LETTER_NUMBER_REGEX = "[\\W]";
    private static final int MIN_LOGIN_PASSWORD_LENGTH = 4;
    private static final int MAX_LOGIN_PASSWORD_LENGTH = 29;
    private static final int MAX_DESCRIPTION_LENGTH = 999;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+$";
    private static final String DESCRIPTION_REGEX = "[\\w,. :;?!'\\\\\"]{1,999}";
    private static final String PHOTO_REFERENCE_REGEX = "^\\w{1,150}\\.png|^\\w{1,}\\.jpg";

    public static UserValidator getInstance() {
        return instance;
    }

    private UserValidator() {

    }

    public boolean validateUser(String username, String email) {
        return username != null && validateStringCharacters(username) &&
                email != null && validateEmail(email);
    }

    public boolean validateDescription(String description) {
        boolean wasValidationSuccessful = false;
        if (description != null) {
            Pattern linePattern = Pattern.compile(NON_LETTER_NUMBER_REGEX);
            Matcher lineMatcher = linePattern.matcher(description);
            if (!lineMatcher.find()) {
                Pattern pattern = Pattern.compile(DESCRIPTION_REGEX);
                Matcher matcher = pattern.matcher(description);
                if (matcher.find() && description.length() < MAX_DESCRIPTION_LENGTH) {
                    wasValidationSuccessful = true;
                }
            }
        }
        return wasValidationSuccessful;
    }

    public boolean validateStringCharacters(String line) {
        boolean isAppropriateLine = true;
        if (line != null) {
            Pattern pattern = Pattern.compile(NON_LETTER_NUMBER_REGEX);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find() || line.length() > MAX_LOGIN_PASSWORD_LENGTH || line.length() < MIN_LOGIN_PASSWORD_LENGTH) {
                isAppropriateLine = false;
            }
        } else {
            isAppropriateLine = false;
        }
        return isAppropriateLine;
    }

    public boolean validatePhotoReference(String fileName) {
        boolean wasSuccessfulValidation = false;
        if (fileName != null) {
            Pattern pattern = Pattern.compile(PHOTO_REFERENCE_REGEX);
            Matcher matcher = pattern.matcher(fileName);
            wasSuccessfulValidation = matcher.find();
        }
        return wasSuccessfulValidation;
    }

    public boolean validateEmail(String email) {
        boolean wasSuccessfulValidation = false;
        if (email != null) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            wasSuccessfulValidation = matcher.find();
        }
        return wasSuccessfulValidation;
    }
}
