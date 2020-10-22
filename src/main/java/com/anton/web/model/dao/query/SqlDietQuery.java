package com.anton.web.model.dao.query;

public class SqlDietQuery {
    public static final String ADD_DIET = "INSERT diets";
    public static final String REMOVE_DIET_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_ALL_DIETS = "SELECT id, name, password, isActive FROM users";
    public static final String SELECT_DIET_BY_NAME =
            "SELECT id, name, password, isActive From users WHERE name=?";

    private SqlDietQuery() {

    }
}
