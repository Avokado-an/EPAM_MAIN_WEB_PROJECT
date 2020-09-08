package com.anton.web_project.model.dao.request;

public class SqlUserRequest {
    public static final String ADD_USER = "INSERT users(name, password, isActive) VALUES (?, ?, ?)";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT id, name, password, isActive FROM users";
    public static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
            "SELECT id, name, password, isActive FROM users WHERE name = ? AND password = ?";
    public static final String SELECT_USER_BY_USERNAME =
            "SELECT id, name, password, isActive From users WHERE name=?";

    // TODO: 03.09.2020  add user block->update methods and select by parameters 

    private SqlUserRequest() {

    }
}
