package com.anton.web_project.model.service.impl;

import com.anton.web_project.model.creator.UserCreator;
import com.anton.web_project.model.dao.impl.UserDaoImplementation;
import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.DaoException;
import com.anton.web_project.model.exception.ServiceException;
import com.anton.web_project.model.service.UserService;
import com.anton.web_project.model.util.PasswordEncoder;

import java.util.Optional;

public class UserServiceImplementation implements UserService {
    private static UserServiceImplementation instance = new UserServiceImplementation();

    public static UserServiceImplementation getInstance() {
        return instance;
    }

    private UserServiceImplementation() {

    }

    @Override
    public boolean isUsernameTaken(String username) throws ServiceException {
        try {
            UserDaoImplementation dao = UserDaoImplementation.getInstance();
            Optional<User> userFromDb = dao.findUser(username);
            return userFromDb.isPresent();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean register(String username, String password) throws ServiceException {
        UserDaoImplementation dao = UserDaoImplementation.getInstance();
        boolean isActive = true; //todo for a while -> make activation and stuff
        boolean registrationResult = false;
        Optional<User> userToAdd = UserCreator.getInstance().createUser(username, password, isActive);
        try {
            if (userToAdd.isPresent()) {
                dao.save(userToAdd.get());
                registrationResult = true;
            }
        } catch (DaoException ex) {
            ex.printStackTrace();
            throw new ServiceException("Can't save user", ex);
        }
        return registrationResult;
    }

    @Override
    public Optional<User> logIn(String username, String password) throws ServiceException {
        UserDaoImplementation dao = UserDaoImplementation.getInstance();
        String codedPassword = PasswordEncoder.encodeString(password);
        try {
            return dao.findUser(username, codedPassword);
        } catch (DaoException ex) {
            ex.printStackTrace();
            throw new ServiceException("Can't login", ex);
        }
    }

    @Override
    public boolean logOut() {
        return true;
    }
}
