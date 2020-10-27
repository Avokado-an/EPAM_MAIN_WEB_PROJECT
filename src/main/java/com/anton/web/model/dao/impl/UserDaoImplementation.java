package com.anton.web.model.dao.impl;

import com.anton.web.model.creator.UserCreator;
import com.anton.web.model.dao.UserDao;
import com.anton.web.model.dao.pool.ConnectionPool;
import com.anton.web.model.dao.query.SqlUserQuery;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImplementation implements UserDao {
    private static UserDaoImplementation instance = new UserDaoImplementation();
    private static final int USER_TYPE_CLIENT_ID = 1;

    public static UserDaoImplementation getInstance() {
        return instance;
    }

    private UserDaoImplementation() {

    }

    @Override
    public void save(User user, String password, int languageId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.ADD_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, password);
            statement.setBoolean(3, user.isActive());
            statement.setInt(4, USER_TYPE_CLIENT_ID);
            statement.setString(5, user.getEmail());
            statement.setInt(6, languageId);
            statement.setString(7, user.getDescription());
            statement.setString(8, user.getPhotoReference());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void remove(int id) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.REMOVE_USER_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void updateActivity(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_USER_ACTIVITY_BY_ID)) {
            statement.setBoolean(1, user.isActive());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void updateMembershipId(String username, int membershipId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_USER_MEMBERSHIP)) {
            statement.setInt(1, membershipId);
            statement.setString(2, username);
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
            ResultSet resultSet = statement.executeQuery(SqlUserQuery.SELECT_ALL_USERS);
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
                     connection.prepareStatement(SqlUserQuery.SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
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
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public List<User> findAllTrainers() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlUserQuery.SELECT_TRAINERS);
            return readUserInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public List<User> findUserTrainers(String username) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.SELECT_USERS_TRAINER)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return readPartialUserInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public List<User> findTrainerUsers(String trainerUsername) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.SELECT_TRAINER_USERS)) {
            statement.setString(1, trainerUsername);
            ResultSet resultSet = statement.executeQuery();
            return readPartialUserInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public Optional<User> findByName(String username) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.SELECT_USER_BY_USERNAME)) {
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

    @Override
    public void updateUsername(String oldName, String newName) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_USERNAME)) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void updateDescription(String username, String description) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_DESCRIPTION)) {
            statement.setString(1, description);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void updatePhotoReference(String username, String photoReference) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_PHOTO_REFERENCE)) {
            statement.setString(1, photoReference);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    @Override
    public void updateUserPosition(String username, int positionId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserQuery.UPDATE_USER_POSITION)) {
            statement.setInt(1, positionId);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        }
    }

    private List<User> readPartialUserInfo(ResultSet resultSet) throws SQLException {
        UserCreator creator = UserCreator.getInstance();
        List<User> trainers = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            String photoReference = resultSet.getString(3);
            Optional<User> userToAdd = creator.createUser(name, description, photoReference);
            userToAdd.ifPresent(trainers::add);
        }
        return trainers;
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
            String description = resultSet.getString(7);
            String photoReference = resultSet.getString(8);
            Optional<User> userToAdd = creator.createUser(id, username, email, userType, isActive, language,
                    description, photoReference);
            userToAdd.ifPresent(users::add);
        }
        return users;
    }
}
