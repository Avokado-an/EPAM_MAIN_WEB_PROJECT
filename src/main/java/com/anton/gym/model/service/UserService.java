package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.exception.TransactionException;
import com.anton.gym.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The {@code UserService} class represents user service.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface UserService {
    /**
     * check if username is already taken
     *
     * @param username the username to check
     * @return if username is taken
     * @throws ServiceException
     */
    boolean isUsernameTaken(String username) throws ServiceException;

    /**
     * register user
     *
     * @param username   the name
     * @param password   the password
     * @param email      the email
     * @param languageId the id of the language
     * @return if user was registered
     * @throws ServiceException
     * @throws TransactionException
     */
    boolean register(String username, String password, String email, int languageId) throws ServiceException, TransactionException;

    /**
     * login user
     *
     * @param username the name
     * @param password the password
     * @return the logged in user
     * @throws ServiceException
     */
    Optional<User> logIn(String username, String password) throws ServiceException;

    /**
     * find user by name
     *
     * @param username the name
     * @return the user
     * @throws ServiceException
     */
    Optional<User> findByUsername(String username) throws ServiceException;

    /**
     * update id of the membership
     *
     * @param username     the name
     * @param membershipId the id of the membership
     * @return if update was successful
     * @throws ServiceException
     * @throws TransactionException
     */
    boolean updateMembershipId(String username, String membershipId) throws ServiceException, TransactionException;

    /**
     * update username
     *
     * @param oldName the old name
     * @param newName the new name
     * @return if name was updated
     * @throws ServiceException
     */
    boolean updateUsername(String oldName, String newName) throws ServiceException;

    /**
     * update description
     *
     * @param username    the name
     * @param description the new description
     * @return if description was succesful
     * @throws ServiceException
     */
    boolean updateDescription(String username, String description) throws ServiceException;

    /**
     * update photo reference
     *
     * @param username       the name
     * @param photoReference the photo reference
     * @return is reference was updated
     * @throws ServiceException
     */
    boolean updatePhotoReference(String username, String photoReference) throws ServiceException;

    /**
     * update trainer id
     *
     * @param username    the name of the user
     * @param trainerName the name of the trainer
     * @return if trainer id was updated
     * @throws ServiceException
     */
    boolean updateTrainerId(String username, String trainerName) throws ServiceException;

    /**
     * updated was account activated
     *
     * @param username     the name
     * @param wasActivated the activation of the account
     * @throws ServiceException
     */
    void updateWasAccountActivated(String username, boolean wasActivated) throws ServiceException;

    /**
     * find all trainers
     *
     * @return trainers
     * @throws ServiceException
     */
    List<User> findAllTrainers() throws ServiceException;

    /**
     * find user trainers
     *
     * @param username the name
     * @return the trainers of the user
     * @throws ServiceException
     */
    List<User> findUserTrainers(String username) throws ServiceException;

    /**
     * find trainer customers
     *
     * @param trainerUsername the name of the user
     * @return the trainers
     * @throws ServiceException
     */
    List<User> findTrainerUsers(String trainerUsername) throws ServiceException;

    /**
     * view sorted trainer customers
     *
     * @param trainerName    the trainer name
     * @param fieldToCompare the field by which to compare
     * @return sorted users
     * @throws ServiceException
     */
    List<User> sortTrainerUsers(String trainerName, String fieldToCompare) throws ServiceException;//todo Service for trainers

    /**
     * search customer of the trainer by username
     *
     * @param trainerName the trainer name
     * @param userToFind  the username to find
     * @return the users
     * @throws ServiceException
     */
    List<User> findTrainerUserByUsername(String trainerName, String userToFind) throws ServiceException;

    /**
     * was user mail activated
     *
     * @param username the name
     * @return if account has been activated
     * @throws ServiceException
     */
    boolean wasUserMailActivated(String username) throws ServiceException;
}
