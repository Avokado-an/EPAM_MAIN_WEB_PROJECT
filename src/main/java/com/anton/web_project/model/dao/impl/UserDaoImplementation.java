package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.creator.UserCreator;
import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.dao.connection.ConnectionPool;
import com.anton.web_project.model.dao.request.SqlUserRequest;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImplementation implements Dao<User> {
    private static UserDaoImplementation instance = new UserDaoImplementation();
    private static final int USER_TYPE_CLIENT_ID = 1;

    public static UserDaoImplementation getInstance() {
        return instance;
    }

    private UserDaoImplementation() {

    }

    @Override
    public void save(User user) throws DaoException {
        //todo think about it
    }

    @Override
    public void save(User user, String password, int languageId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.ADD_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, password);
            statement.setBoolean(3, user.isActive());
            statement.setInt(4, USER_TYPE_CLIENT_ID);
            statement.setString(5, user.getEmail());
            statement.setInt(6, languageId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can't connect to db", ex); // TODO: 03.09.2020 make username field that cant be repeated in db
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.REMOVE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.UPDATE_USER_ACTIVITY_BY_ID)) {
            statement.setBoolean(1, user.isActive());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlUserRequest.SELECT_ALL_USERS);
            return readUserInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public Optional<User> find(String username, String password) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SqlUserRequest.SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            List<User> user = readUserInfo(resultSet);
            Optional<User> userToLogin = Optional.empty();
            if (!user.isEmpty()) {
                userToLogin = Optional.of(user.get(0));
            }
            return userToLogin;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public Optional<User> findByName(String username) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.SELECT_USER_BY_USERNAME)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            List<User> user = readUserInfo(resultSet);
            Optional<User> userToLogin = Optional.empty();
            if (!user.isEmpty()) {
                userToLogin = Optional.of(user.get(0));
            }
            return userToLogin;
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    private List<User> readUserInfo(ResultSet resultSet) throws SQLException {
        UserCreator creator = UserCreator.getInstance();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            boolean isActive = resultSet.getBoolean(3);
            String userType = resultSet.getString(4);
            String email = resultSet.getString(5);
            String language = resultSet.getString(6);
            Optional<User> userToAdd = creator.createUser(id, username, email, userType, isActive, language);
            userToAdd.ifPresent(users::add);
        }
        return users;
    }
}
