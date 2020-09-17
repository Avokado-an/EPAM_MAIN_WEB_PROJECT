package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Trainer;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class TrainerDaoImplementation implements Dao<Trainer> {
    @Override
    public void save(Trainer trainer) throws DaoException {

    }

    @Override
    public void save(Trainer trainer, String extraData) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Trainer trainer) {

    }

    @Override
    public Optional<Trainer> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Trainer> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> find(String parameter1, String parameter2) throws DaoException {
        return Optional.empty();
    }
}
