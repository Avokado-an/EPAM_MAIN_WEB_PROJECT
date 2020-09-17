package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Sale;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class SaleDaoImplementation implements Dao<Sale> {
    @Override
    public void save(Sale sale) throws DaoException {

    }

    @Override
    public void save(Sale sale, String extraData) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Sale sale) {

    }

    @Override
    public Optional<Sale> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Sale> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> find(String parameter1, String parameter2) throws DaoException {
        return Optional.empty();
    }
}
