package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.User;

import java.util.List;

/**
 * The {@code AdminService} class represents admin service.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface AdminService {
    /**
     * block the user
     *
     * @param username the username
     * @return if user was blocked
     * @throws ServiceException
     */
    boolean blockUser(String username) throws ServiceException;

    /**
     * unblock user
     *
     * @param username the username
     * @return if user was unblocked
     * @throws ServiceException
     */
    boolean unblockUser(String username) throws ServiceException;

    /**
     * view all users
     *
     * @return the users
     * @throws ServiceException
     */
    List<User> viewUsers() throws ServiceException;

    /**
     * delete user by id
     *
     * @param id the id
     * @throws ServiceException
     */
    void deleteUserById(int id) throws ServiceException;

    /**
     * change the position of the user
     *
     * @param username   the name
     * @param positionId the id of the position
     * @return if user position was changed
     * @throws ServiceException
     */
    boolean changeUserPosition(String username, int positionId) throws ServiceException;

    /**
     * sort all users
     *
     * @param fieldToCompare the field by which to compare
     * @return sorted users
     * @throws ServiceException
     */
    List<User> sortAllUsers(String fieldToCompare) throws ServiceException;

    /**
     * find users by username
     *
     * @param username the username
     * @return searched users
     * @throws ServiceException
     */
    List<User> findUsersByUsername(String username) throws ServiceException;
}
