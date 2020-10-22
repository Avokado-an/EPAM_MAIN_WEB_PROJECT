package com.anton.web.model.dao.query;

public class SqlUserQuery {
    public static final String ADD_USER = "INSERT users(name, password, isActive, type_id, email, language_id," +
            " description, photo_reference) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT users.id, users.name, users.isActive, user_role.position, users.email, " +
            "languages.name, users.description, users.photo_reference FROM users " +
            "INNER JOIN user_role ON users.type_id = user_role.id " +
            "INNER JOIN languages ON users.language_id = languages.id";
    public static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
            SELECT_ALL_USERS + " WHERE users.name = ? AND users.password = ?";
    public static final String SELECT_USER_BY_USERNAME = SELECT_ALL_USERS + " WHERE users.name = ?";
    public static final String UPDATE_USER_ACTIVITY_BY_ID = "UPDATE users SET users.isActive = ? WHERE users.id = ?";
    public static final String UPDATE_USER_MEMBERSHIP = "UPDATE users SET users.membership_id = ? WHERE users.name = ?";
    public static final String SELECT_TRAINERS = SELECT_ALL_USERS + " WHERE users.type_id = 2";
    public static final String SELECT_USERS_TRAINER =
            "select trainer.name, trainer.description, trainer.photo_reference from users consumer, users trainer WHERE consumer.trainer_id = trainer.id AND consumer.name = ?";
    public static final String SELECT_TRAINER_USERS =
            "select user.name, user.description, user.photo_reference from users consumer, users trainer WHERE consumer.trainer_id = trainer.id AND trainer.name = ?";


    private SqlUserQuery() {

    }
}
