package com.anton.web_project.model.service;

import com.anton.web_project.model.entity.User;
import com.anton.web_project.model.exception.ServiceException;

import java.util.Optional;

public interface UserService {
    boolean isUsernameTaken(String username) throws ServiceException;
    boolean register(String username, String password, String email) throws ServiceException;
    Optional<User> logIn(String username, String password) throws ServiceException;
    boolean logOut();
}
