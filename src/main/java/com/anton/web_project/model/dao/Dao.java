package com.anton.web_project.model.dao;

import com.anton.web_project.model.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    void save(T t) throws DaoException;
    void remove(int id) throws DaoException;
    void update(T t);
    //T find(String searchParameter);
    List<T> findAll() throws DaoException;
}
