package com.anton.web.model.service.impl;

import com.anton.web.model.dao.UserDao;
import com.anton.web.model.dao.impl.UserDaoImplementation;
import com.anton.web.model.entity.User;
import com.anton.web.model.entity.UserType;
import com.anton.web.model.exception.DaoException;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.AdminService;

import java.util.List;
import java.util.Optional;

public class AdminServiceImplementation implements AdminService {
    private static AdminServiceImplementation instance = new AdminServiceImplementation();

    public static AdminServiceImplementation getInstance() {
        return instance;
    }

    private AdminServiceImplementation() {

    }

    @Override
    public boolean blockUser(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean wasUserBlocked = true;// TODO: 13.10.2020 rename all flags
        try {
            Optional<User> userToBlock = dao.findByName(username);
            if (userToBlock.isPresent()) {
                if (userToBlock.get().getType() != UserType.ADMIN) {
                    userToBlock.get().setActive(false);
                    dao.updateActivity(userToBlock.get());
                } else {
                    wasUserBlocked = false;
                }
            } else {
                wasUserBlocked = false;
            }
            return wasUserBlocked;
        } catch (DaoException e) {
            throw new ServiceException("can't block user", e);
        }
    }

    @Override
    public boolean unblockUser(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean wasUserUnblocked = true;
        try {
            Optional<User> userToUnblock = dao.findByName(username);
            if (userToUnblock.isPresent()) {
                userToUnblock.get().setActive(true);
                dao.updateActivity(userToUnblock.get());
            } else {
                wasUserUnblocked = false;
            }
            return wasUserUnblocked;
        } catch (DaoException e) {
            throw new ServiceException("can't unblock user", e);
        }
    }

    @Override
    public List<User> viewUsers() throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("can't view all users", e);
        }
    }

    @Override
    public void deleteUserById(int id) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            dao.remove(id);
        } catch (DaoException e) {
            throw new ServiceException("can't delete user", e);
        }
    }

    @Override
    public boolean changeUserPosition(String username, int userTypeId) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean wasUserPositionChanged = true;
        try {
            Optional<User> userToMark = dao.findByName(username);
            if (userToMark.isPresent()) {
                if (userToMark.get().getType() != UserType.ADMIN) {
                    dao.updateUserPosition(userToMark.get().getUsername(), userTypeId);
                } else {
                    wasUserPositionChanged = false;
                }
            } else {
                wasUserPositionChanged = false;
            }
            return wasUserPositionChanged;
        } catch (DaoException e) {
            throw new ServiceException("can't change user position", e);
        }
    }
}
