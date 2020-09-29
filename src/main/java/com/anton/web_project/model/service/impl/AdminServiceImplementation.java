package com.anton.web_project.model.service.impl;

import com.anton.web_project.model.dao.Dao;
import com.anton.web_project.model.dao.UserDao;
import com.anton.web_project.model.dao.impl.UserDaoImplementation;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.AdminService;

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
        boolean flag = true;
        try {
            Optional<User> userToBlock = dao.findByName(username);
            if (userToBlock.isPresent()) {
                userToBlock.get().setActive(false);
                dao.update(userToBlock.get());
            } else {
                flag = false;
            }
            return flag;
        } catch (DaoException e) {
            throw new ServiceException("can't block user", e);
        }
    }

    @Override
    public boolean unblockUser(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean flag = true;
        try {
            Optional<User> userToUnblock = dao.findByName(username);
            if (userToUnblock.isPresent()) {
                userToUnblock.get().setActive(true);
                dao.update(userToUnblock.get());
            } else {
                flag = false;
            }
            return flag;
        } catch (DaoException e) {
            throw new ServiceException("can't block user", e);
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
}
