package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;

import java.util.List;

public interface AdminService {
    boolean blockUser(String username) throws ServiceException;

    boolean unblockUser(String username) throws ServiceException;

    List<User> viewUsers() throws ServiceException;

    void deleteUserById(int id) throws ServiceException;

    boolean changeUserPosition(String username, int positionId) throws ServiceException;

    List<User> sortAllUsers(String fieldToCompare) throws ServiceException;

    List<User> findUsersByUsername(String username) throws ServiceException;
}
