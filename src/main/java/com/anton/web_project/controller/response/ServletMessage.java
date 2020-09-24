package com.anton.web_project.controller.response;

public class ServletMessage {
    public static final String ERROR_MESSAGE = "Ooops, something went wrong";
    public static final String OPERATION_FAILED = "Action failed";
    public static final String OPERATION_SUCCEED = "Action performed successfully";
    public static final String WRONG_PASSWORD_OR_USERNAME_OR_EMAIL = "Wrong password or username or email.\n" +
            "Use a-Z, 0-9, _ symbols for username and password, use your valid email";
    public static final String WRONG_PASSWORD_OR_USERNAME = "Wrong password or username";
    public static final String USERNAME_IS_TAKEN = "Username is taken";
    public static final String USER_NOT_ACTIVE = "Your account is not active. Activate it via email or ask admin";

    private ServletMessage() {
    }
}
