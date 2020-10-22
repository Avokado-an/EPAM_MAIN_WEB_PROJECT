package com.anton.web.model.service;

import com.anton.web.model.entity.Membership;
import com.anton.web.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MembershipService {
    List<Membership> findMemberships() throws ServiceException;

    Optional<Membership> findUsersMembership(String username) throws ServiceException;

    Optional<Membership> find(String name) throws ServiceException;

    boolean update(Membership membership) throws ServiceException;
}
