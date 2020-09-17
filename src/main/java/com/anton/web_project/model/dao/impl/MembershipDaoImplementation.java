package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Membership;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class MembershipDaoImplementation implements Dao<Membership> {
    @Override
    public void save(Membership membership) throws DaoException {

    }

    @Override
    public void save(Membership membership, String extraData) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Membership membership) {

    }

    @Override
    public Optional<Membership> findByName(String name) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Membership> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> find(String parameter1, String parameter2) throws DaoException {
        return Optional.empty();
    }
}
