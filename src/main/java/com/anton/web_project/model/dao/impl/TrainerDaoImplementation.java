package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Trainer;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;

public class TrainerDaoImplementation implements Dao<Trainer> {
    @Override
    public void save(Trainer trainer) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Trainer trainer) {

    }

    @Override
    public List<Trainer> findAll() throws DaoException {
        return null;
    }
}
