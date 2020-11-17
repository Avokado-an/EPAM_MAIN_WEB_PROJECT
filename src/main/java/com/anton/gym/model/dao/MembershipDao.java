package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;
import com.anton.gym.model.entity.Membership;

import java.util.List;
import java.util.Optional;

/**
 * The {@code MembershipDao} class represents membership dao.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface MembershipDao {
    /**
     * saves membership into db
     *
     * @param membership the membership to save
     * @throws DaoException
     */
    void save(Membership membership) throws DaoException;

    /**
     * remove membership form db by id
     *
     * @param id the id of membership to delete
     * @throws DaoException
     */
    void remove(int id) throws DaoException;

    /**
     * update membership
     *
     * @param membership the membership data to update
     * @throws DaoException
     */
    void update(Membership membership) throws DaoException;

    /**
     * find all memberships
     *
     * @return the memberships
     * @throws DaoException
     */
    List<Membership> findAll() throws DaoException;

    /**
     * find all active memberships
     *
     * @return active memberships
     * @throws DaoException
     */
    List<Membership> findAllActive() throws DaoException;

    /**
     * find membership by name
     *
     * @param name the name of membership to find
     * @return the membership
     * @throws DaoException
     */
    Optional<Membership> findByName(String name) throws DaoException;

    /**
     * find membership by id
     *
     * @param id the id of membership to find
     * @return the membership
     * @throws DaoException
     */
    Optional<Membership> findById(int id) throws DaoException;

    /**
     * find membership by username of owner
     *
     * @param username the username of user whose membership to find
     * @return the membership
     * @throws DaoException
     */
    Optional<Membership> findByUser(String username) throws DaoException;
}
