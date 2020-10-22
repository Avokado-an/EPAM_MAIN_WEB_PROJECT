package com.anton.web.model.dao.query;

public class SqlMembershipQuery {
    public static final String ADD_MEMBERSHIP = "INSERT memberships(name, amount_of_attendees, price, months) VALUES (?, ?, ?, ?)";
    public static final String REMOVE_MEMBERSHIP_BY_ID = "DELETE FROM memberships WHERE id = ?";
    public static final String SELECT_ALL_MEMBERSHIPS = "SELECT memberships.id, memberships.name, memberships.price, " +
            "memberships.amount_of_attendees, memberships.months FROM memberships";
    public static final String SELECT_MEMBERSHIPS_OF_USER =
            SELECT_ALL_MEMBERSHIPS + " INNER JOIN users ON users.membership_id = memberships.id AND users.name = ?";
    public static final String SELECT_BY_NAME = SELECT_ALL_MEMBERSHIPS + " WHERE memberships.name = ?";
    public static final String UPDATE_MEMBERSHIP = "UPDATE memberships SET name = ?, amount_of_attendees = ?, price = ?, " +
            "months = ? WHERE id = ?";

    private SqlMembershipQuery() {

    }
}
