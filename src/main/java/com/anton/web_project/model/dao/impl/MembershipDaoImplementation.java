package com.anton.web_project.model.dao.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.entity.Membership;
import com.anton.web_project.model.exception.DaoException;

import java.util.List;

public class MembershipDaoImplementation implements Dao<Membership> {
    @Override
    public void save(Membership membership) throws DaoException {

    }

    @Override
    public void remove(int id) throws DaoException {

    }

    @Override
    public void update(Membership membership) {

    }

    @Override
    public List<Membership> findAll() throws DaoException {
        return null;
    }
}
