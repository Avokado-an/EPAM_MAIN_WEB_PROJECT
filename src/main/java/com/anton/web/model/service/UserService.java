package com.anton.web.model.service;

import com.anton.web.model.entity.User;
import com.anton.web.model.exception.DaoException;
import com.anton.web.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isUsernameTaken(String username) throws ServiceException;

    boolean register(String username, String password, String email, int languageId) throws ServiceException;

    Optional<User> logIn(String username, String password) throws ServiceException;

    boolean logOut();

    boolean updateMembershipId(String username, String membershipId) throws ServiceException;

    Optional<String> defineUserLanguage(String username) throws ServiceException;

    List<User> findAllTrainers() throws ServiceException;

    List<User> findUserTrainers(String username) throws ServiceException;

    List<User> findTrainerUsers(String trainerUsername) throws ServiceException;
}
