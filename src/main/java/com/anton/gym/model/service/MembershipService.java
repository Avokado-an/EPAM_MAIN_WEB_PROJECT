package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Membership;

import java.util.List;
import java.util.Optional;

/**
 * The {@code MembershipService} class represents membership service.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface MembershipService {
    /**
     * find all memberships
     *
     * @return the memberships
     * @throws ServiceException
     */
    List<Membership> findMemberships() throws ServiceException;

    /**
     * find membership of the user
     *
     * @param username the username
     * @return the membership
     * @throws ServiceException
     */
    Optional<Membership> findUsersMembership(String username) throws ServiceException;

    /**
     * find by name
     *
     * @param name the name of the membership
     * @return the membership
     * @throws ServiceException
     */
    Optional<Membership> find(String name) throws ServiceException;

    /**
     * find by id
     *
     * @param id the id of the membership
     * @return the membership
     * @throws ServiceException
     */
    Optional<Membership> find(int id) throws ServiceException;

    /**
     * find all active memberships
     *
     * @return memberships
     * @throws ServiceException
     */
    List<Membership> findActiveMemberships() throws ServiceException;

    /**
     * add new membership
     *
     * @param membership the membership to add
     * @throws ServiceException
     */
    void addMembership(Membership membership) throws ServiceException;

    /**
     * update membership
     *
     * @param membership the membership to update
     * @return was membership updated
     * @throws ServiceException
     */
    boolean update(Membership membership) throws ServiceException;
}
