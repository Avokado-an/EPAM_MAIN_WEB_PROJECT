package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Diet;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class DietDaoImplementation implements Dao<Diet> {
    @Override
    public void save(Diet diet) throws DaoException {

    }

    @Override
    public void save(Diet diet, String extraData, int a) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Diet diet) {

    }

    @Override
    public Optional<Diet> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Diet> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> find(String parameter1, String parameter2) throws DaoException {
        return Optional.empty();
    }
}
