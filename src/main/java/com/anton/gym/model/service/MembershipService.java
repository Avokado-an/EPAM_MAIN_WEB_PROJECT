package com.anton.gym.model.service;

import com.anton.gym.model.entity.Membership;
import com.anton.gym.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    List<Membership> findMemberships() throws ServiceException;

    Optional<Membership> findUsersMembership(String username) throws ServiceException;

    Optional<Membership> find(String name) throws ServiceException;

    void addMembership(Membership membership) throws ServiceException;

    boolean update(Membership membership) throws ServiceException;
}
