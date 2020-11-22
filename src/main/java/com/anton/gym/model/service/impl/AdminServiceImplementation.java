package com.anton.gym.model.service.impl;

import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.dao.UserDao;
import com.anton.gym.model.dao.impl.UserDaoImplementation;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.entity.UserType;
import com.anton.gym.model.service.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code AdminServiceImplementation} class represents admin service implementation.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class AdminServiceImplementation implements AdminService {
    private static final AdminServiceImplementation instance = new AdminServiceImplementation();

    public static AdminServiceImplementation getInstance() {
        return instance;
    }

    private AdminServiceImplementation() {

    }

    @Override
    public boolean blockUser(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean wasUserBlocked = true;
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
    public List<User> sortAllUsers(String fieldToCompare) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            return dao.sortUsers(fieldToCompare);
        } catch (DaoException e) {
            throw new ServiceException("can't view all users", e);
        }
    }

    @Override
    public List<User> findUsersByUsername(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            List<User> allUsers = dao.findAll();
            List<User> searchedUsers = new ArrayList<>();
            allUsers.stream().filter(user -> user.getUsername().contains(username)).forEach(searchedUsers::add);
            return searchedUsers;
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
