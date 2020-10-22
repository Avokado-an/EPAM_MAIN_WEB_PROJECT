package com.anton.web.model.dao;

import com.anton.web.model.entity.Membership;
import com.anton.web.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MembershipDao {
    void save(Membership membership) throws DaoException;

    void remove(int id) throws DaoException;

    void update(Membership membership) throws DaoException;

    List<Membership> findAll() throws DaoException;

    Optional<Membership> findByName(String name) throws DaoException;

    Optional<Membership> findByUser(String username) throws DaoException;
}
