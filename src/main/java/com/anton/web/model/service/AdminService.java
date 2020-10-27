package com.anton.web.model.service;

import com.anton.web.model.entity.User;
import com.anton.web.model.exception.ServiceException;

import java.util.List;

public interface AdminService {
    boolean blockUser(String username) throws ServiceException;

    boolean unblockUser(String username) throws ServiceException;

    List<User> viewUsers() throws ServiceException;

    void deleteUserById(int id) throws ServiceException;

    boolean changeUserPosition(String username, int positionId) throws ServiceException;
}
