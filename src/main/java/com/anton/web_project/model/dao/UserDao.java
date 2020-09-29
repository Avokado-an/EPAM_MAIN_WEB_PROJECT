package com.anton.web_project.model.dao;

import com.anton.web_project.model.creator.UserCreator;
import com.anton.web_project.model.dao.connection.ConnectionPool;
import com.anton.web_project.model.dao.request.SqlUserRequest;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user, String password, int languageId) throws DaoException;

    void remove(int id) throws DaoException;

    void update(User user) throws DaoException;

    List<User> findAll() throws DaoException;

    Optional<User> find(String username, String password) throws DaoException;

    Optional<User> findByName(String username) throws DaoException;
}
