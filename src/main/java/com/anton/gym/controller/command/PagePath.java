package com.anton.gym.controller.command;

/**
 * The {@code PagePath} class represents jsp paths.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class PagePath {
    public static final String ERROR = "/jsp/error.jsp";
    public static final String LOGIN = "/jsp/login.jsp";
    public static final String USER_PROFILE = "/jsp/user_profile.jsp";
    public static final String REGISTRATION = "/jsp/registration.jsp";
    public static final String VIEW_USERS = "/jsp/view_users.jsp";
    public static final String MAIN = "/jsp/main.jsp";
    public static final String REDACT_MEMBERSHIP = "/jsp/redact_membership.jsp";
    public static final String REDACT_USER_PROFILE = "/jsp/redact_profile.jsp";
    public static final String PAGE_NOT_FOUND_ERROR = "/jsp/unknown_url_error.jsp";
    public static final String REPLENISH_BALANCE = "/jsp/replenish_balance.jsp";

    private PagePath() {

    }
}
