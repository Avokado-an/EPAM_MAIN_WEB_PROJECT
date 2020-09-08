package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Sale;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;

public class SaleDaoImplementation implements Dao<Sale> {
    @Override
    public void save(Sale sale) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Sale sale) {

    }

    @Override
    public List<Sale> findAll() throws DaoException {
        return null;
    }
}
