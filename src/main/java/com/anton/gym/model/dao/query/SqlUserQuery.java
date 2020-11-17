package com.anton.gym.model.dao.query;

/**
 * The {@code SqlUserQuery} class represents Sql User Query.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class SqlUserQuery {
    public static final String ADD_USER = "INSERT users(name, password, is_active, type_id, email, language_id, " +
            "description, photo_reference) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT users.id, users.name, users.is_active, user_role.position, users.email, " +
            "languages.language, users.description, users.photo_reference FROM users " +
            "INNER JOIN user_role ON users.type_id = user_role.id " +
            "INNER JOIN languages ON users.language_id = languages.id";
    public static final String SELECT_USER_BY_USERNAME_AND_PASSWORD =
            SELECT_ALL_USERS + " WHERE users.name = ? AND users.password = ?";
    public static final String SELECT_USER_BY_USERNAME = SELECT_ALL_USERS + " WHERE users.name = ?";
    public static final String UPDATE_USER_ACTIVITY_BY_ID = "UPDATE users SET users.is_active = ? WHERE users.id = ?";
    public static final String UPDATE_USER_MEMBERSHIP = "UPDATE users SET users.membership_id = ? WHERE users.name = ?";
    public static final String SELECT_TRAINERS = SELECT_ALL_USERS + " WHERE users.type_id = 2 AND users.is_active = true";
    public static final String SELECT_USERS_TRAINER =
            "SELECT trainer.name, trainer.description, trainer.photo_reference, trainer.is_active " +
                    "FROM users consumer, users trainer WHERE consumer.trainer_id = trainer.id AND consumer.name = ?";
    public static final String SELECT_TRAINER_USERS = "SELECT consumer.name, consumer.is_active FROM users consumer, " +
            "users trainer WHERE consumer.trainer_id = trainer.id AND trainer.name = ?";
    public static final String UPDATE_USERNAME = "UPDATE users SET users.name = ? WHERE users.name = ?";
    public static final String UPDATE_DESCRIPTION = "UPDATE users SET users.description = ? WHERE users.name = ?";
    public static final String UPDATE_PHOTO_REFERENCE = "UPDATE users SET users.photo_reference = ? WHERE users.name = ?";
    public static final String UPDATE_USER_POSITION = "UPDATE users SET users.type_id = ? WHERE users.name = ?";
    public static final String UPDATE_USER_WAS_ACCOUNT_ACTIVATED =
            "UPDATE users SET users.was_mail_activated = ? WHERE users.name = ?";
    public static final String UPDATE_TRAINER_ID = "UPDATE users SET users.trainer_id = ? WHERE users.name = ?";
    public static final String SORT_ALL_USERS = SELECT_ALL_USERS + " ORDER BY ";
    public static final String SORT_TRAINER_USERS = SELECT_TRAINER_USERS + " ORDER BY ";
    public static final String SELECT_WAS_USER_ACTIVATED = "SELECT users.was_mail_activated FROM users WHERE name = ?";

    private SqlUserQuery() {

    }
}
