package com.anton.web.model.service.impl;

import com.anton.web.model.creator.UserCreator;
import com.anton.web.model.dao.UserDao;
import com.anton.web.model.dao.impl.UserDaoImplementation;
import com.anton.web.model.entity.User;
import com.anton.web.model.exception.DaoException;
import com.anton.web.model.exception.ServiceException;
import com.anton.web.model.service.UserService;
import com.anton.web.model.util.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
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
            UserDao dao = UserDaoImplementation.getInstance();
            Optional<User> userFromDb = dao.findByName(username);
            return userFromDb.isPresent();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean register(String username, String password, String email, int languageId) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        boolean isActive = false;
        boolean registrationResult = false;
        Optional<User> userToAdd = UserCreator.getInstance().createUser(username, email, isActive);
        String encodedPassword = PasswordEncoder.encodeString(password);
        try {
            if (userToAdd.isPresent()) {
                dao.save(userToAdd.get(), encodedPassword, languageId);
                registrationResult = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't save user", ex);
        }
        return registrationResult;
    }

    @Override
    public Optional<User> logIn(String username, String password) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        String codedPassword = PasswordEncoder.encodeString(password);
        try {
            return dao.find(username, codedPassword);
        } catch (DaoException ex) {
            throw new ServiceException("Can't login", ex);
        }
    }

    @Override
    public boolean logOut() {
        return true; //todo what to do here???
    }

    @Override
    public boolean updateMembershipId(String username, String membershipId) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        boolean wasMembershipIdUpdated = true;
        try {
            int id = Integer.parseInt(membershipId);
            userDao.updateMembershipId(username, id);
        } catch (NumberFormatException e) {
            wasMembershipIdUpdated = false;
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasMembershipIdUpdated;
    }

    @Override
    public Optional<String> defineUserLanguage(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        Optional<String> language = Optional.empty();
        try {
            Optional<User> optionalUser = dao.findByName(username);
            if (optionalUser.isPresent()) {
                language = Optional.of(optionalUser.get().getLanguage());
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't login", ex);
        }
        return language;
    }

    @Override
    public List<User> findAllTrainers() throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> trainers;
        try {
            trainers = userDao.findAllTrainers();
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return trainers;
    }

    @Override
    public List<User> findUserTrainers(String username) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> trainers;
        try {
            trainers = userDao.findUserTrainers(username);
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return trainers;
    }

    @Override
    public List<User> findTrainerUsers(String trainerUsername) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> users;
        try {
            users = userDao.findTrainerUsers(trainerUsername);
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return users;
    }
}
