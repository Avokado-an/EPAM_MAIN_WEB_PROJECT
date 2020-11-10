package com.anton.gym.model.service.impl;

import com.anton.gym.model.dao.MembershipDao;
import com.anton.gym.model.dao.impl.MembershipDaoImplementation;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.service.MembershipService;

import java.util.List;
import java.util.Optional;

public class MembershipServiceImplementation implements MembershipService {
    private static MembershipServiceImplementation instance;

    public static MembershipServiceImplementation getInstance() {
        if (instance == null) {
            instance = new MembershipServiceImplementation();
        }
        return instance;
    }

    private MembershipServiceImplementation() {

    }

    @Override
    public List<Membership> findMemberships() throws ServiceException {
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        try {
            return membershipDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Membership> findUsersMembership(String username) throws ServiceException {
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        try {
            return membershipDao.findByUser(username);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Membership> find(String name) throws ServiceException {
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        try {
            return membershipDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addMembership(Membership membership) throws ServiceException {
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        try {
            membershipDao.save(membership);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Membership membership) throws ServiceException {
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        boolean wasUpdated = false;
        try {
            Optional<Membership> optMembership = membershipDao.findByName(membership.getName());
            if (optMembership.isPresent()) {
                membershipDao.update(membership);
                wasUpdated = true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return wasUpdated;
    }
}
