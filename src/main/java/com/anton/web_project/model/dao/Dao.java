package com.anton.web_project.model.dao;

import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void save(T t) throws DaoException;
    //void save(T t, String extraData) throws DaoException;
    void save(T t, String stringExtraData, int intExtraData) throws DaoException;
    void remove(int id) throws DaoException;
    void update(T t) throws DaoException;
    Optional<T> findByName(String name) throws DaoException;
    List<T> findAll() throws DaoException;
    Optional<User> find(String parameter1, String parameter2) throws DaoException;
}
