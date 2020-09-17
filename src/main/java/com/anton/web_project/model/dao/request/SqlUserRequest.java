package com.anton.web_project.model.dao.request;

public class SqlUserRequest {
    public static final String ADD_USER = "INSERT users(name, password, isActive, type_id, email) VALUES (?, ?, ?, ?, ?)";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT users.id, users.name, users.isActive, user_role.position, users.email " +
            "FROM users INNER JOIN user_role ON users.type_id = user_role.id";
    public static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
            SELECT_ALL_USERS + " WHERE name = ? AND password = ?";
    public static final String SELECT_USER_BY_USERNAME = SELECT_ALL_USERS + " WHERE name = ?";
    public static final String UPDATE_USER_ACTIVITY_BY_ID = "UPDATE users SET isActive = ? WHERE id = ?";

    private SqlUserRequest() {

    }
}
