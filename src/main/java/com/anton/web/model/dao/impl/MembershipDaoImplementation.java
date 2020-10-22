package com.anton.web.model.dao.impl;

import com.anton.web.model.creator.MembershipCreator;
import com.anton.web.model.dao.MembershipDao;
import com.anton.web.model.dao.pool.ConnectionPool;
import com.anton.web.model.dao.query.SqlMembershipQuery;
import com.anton.web.model.entity.Membership;
import com.anton.web.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MembershipDaoImplementation implements MembershipDao {
    private static MembershipDaoImplementation instance;

    public static MembershipDaoImplementation getInstance() {
        if (instance == null) {
            instance = new MembershipDaoImplementation();
        }
        return instance;
    }

    private MembershipDaoImplementation() {

    }

    @Override
    public void save(Membership membership) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlMembershipQuery.ADD_MEMBERSHIP)) {
            statement.setString(1, membership.getName());
            statement.setInt(2, membership.getPrice());
            statement.setString(3, membership.getAmountOfAttendees());
            statement.setInt(4, membership.getMonths());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlMembershipQuery.REMOVE_MEMBERSHIP_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void update(Membership membership) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlMembershipQuery.UPDATE_MEMBERSHIP)) {
            statement.setString(1, membership.getName());
            statement.setString(2, membership.getAmountOfAttendees());
            statement.setInt(3, membership.getPrice());
            statement.setInt(4, membership.getMonths());
            statement.setInt(5, membership.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public List<Membership> findAll() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlMembershipQuery.SELECT_ALL_MEMBERSHIPS);
            return readMembershipInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public Optional<Membership> findByName(String name) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlMembershipQuery.SELECT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            List<Membership> user = readMembershipInfo(resultSet);
            Optional<Membership> membershipToFind = Optional.empty();
            if (!user.isEmpty()) {
                membershipToFind = Optional.of(user.get(0));
            }
            return membershipToFind;
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public Optional<Membership> findByUser(String username) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlMembershipQuery.SELECT_MEMBERSHIPS_OF_USER)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return readMembershipInfo(resultSet).stream().findFirst();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    private List<Membership> readMembershipInfo(ResultSet resultSet) throws SQLException {
        MembershipCreator creator = MembershipCreator.getInstance();
        List<Membership> memberships = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int price = resultSet.getInt(3);
            String amountOfVisits = resultSet.getString(4);
            int months = resultSet.getInt(5);
            Optional<Membership> membershipToAdd = creator.createMembership(id, name, price, amountOfVisits, months);
            membershipToAdd.ifPresent(memberships::add);
        }
        return memberships;
    }
}
