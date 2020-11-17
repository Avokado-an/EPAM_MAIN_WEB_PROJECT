package com.anton.gym.model.dao.query;

/**
 * The {@code SqlMembershipQuery} class represents Sql Membership Query.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class SqlMembershipQuery {
    public static final String ADD_MEMBERSHIP =
            "INSERT memberships(name, amount_of_attendees, price, months, is_active) VALUES (?, ?, ?, ?, ?)";
    public static final String REMOVE_MEMBERSHIP_BY_ID = "DELETE FROM memberships WHERE id = ?";
    public static final String SELECT_ALL_MEMBERSHIPS = "SELECT memberships.id, memberships.name, memberships.price, " +
            "memberships.amount_of_attendees, memberships.months, memberships.is_active FROM memberships";
    public static final String SELECT_ALL_ACTIVE_MEMBERSHIPS = SELECT_ALL_MEMBERSHIPS + " WHERE memberships.is_active = 1";
    public static final String SELECT_MEMBERSHIPS_OF_USER =
            SELECT_ALL_MEMBERSHIPS + " INNER JOIN users ON users.membership_id = memberships.id AND users.name = ?";
    public static final String SELECT_BY_NAME = SELECT_ALL_MEMBERSHIPS + " WHERE memberships.name = ?";
    public static final String SELECT_BY_ID = SELECT_ALL_MEMBERSHIPS + " WHERE memberships.id = ?";
    public static final String UPDATE_MEMBERSHIP = "UPDATE memberships SET name = ?, amount_of_attendees = ?, " +
            "price = ?, months = ?, is_active = ? WHERE id = ?";

    private SqlMembershipQuery() {

    }
}
