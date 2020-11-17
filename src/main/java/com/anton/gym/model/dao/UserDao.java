package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;
import com.anton.gym.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The {@code MembershipDao} class represents membership dao.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface UserDao {
    /**
     * save user in db
     *
     * @param user       the user to save in db
     * @param password   the password of the user
     * @param languageId the language id
     * @throws DaoException
     */
    void save(User user, String password, int languageId) throws DaoException;

    /**
     * remove user by id
     *
     * @param id the id of the user to delete
     * @throws DaoException
     */
    void remove(int id) throws DaoException;

    /**
     * update activity of the user
     *
     * @param user the user whose activity to update
     * @throws DaoException
     */
    void updateActivity(User user) throws DaoException;

    /**
     * update user membership id
     *
     * @param username     the name of the user
     * @param membershipId the id of the membership
     * @throws DaoException
     */
    void updateMembershipId(String username, int membershipId) throws DaoException;

    /**
     * find all users
     *
     * @return all users
     * @throws DaoException
     */
    List<User> findAll() throws DaoException;

    /**
     * find user by name and password
     *
     * @param username the name
     * @param password the password
     * @return the user which was found
     * @throws DaoException
     */
    Optional<User> find(String username, String password) throws DaoException;

    /**
     * find trainers
     *
     * @return trainers
     * @throws DaoException
     */
    List<User> findAllTrainers() throws DaoException;

    /**
     * find trainers of the user
     *
     * @param username the name
     * @return tha user trainers
     * @throws DaoException
     */
    List<User> findUserTrainers(String username) throws DaoException;

    /**
     * find trainer customers
     *
     * @param trainerUsername the name of the trainer
     * @return the customers of the trainer
     * @throws DaoException
     */
    List<User> findTrainerUsers(String trainerUsername) throws DaoException;

    /**
     * find user by username
     *
     * @param username the name
     * @return the user
     * @throws DaoException
     */
    Optional<User> findByName(String username) throws DaoException;

    /**
     * update users username
     *
     * @param oldName the previous name
     * @param newName the new name
     * @throws DaoException
     */
    void updateUsername(String oldName, String newName) throws DaoException;

    /**
     * update user description
     *
     * @param username    the name of the user
     * @param description the new description
     * @throws DaoException
     */
    void updateDescription(String username, String description) throws DaoException;

    /**
     * update user photo reference
     *
     * @param username       the username
     * @param photoReference the new photo reference
     * @throws DaoException
     */
    void updatePhotoReference(String username, String photoReference) throws DaoException;

    /**
     * update user position
     *
     * @param username   the username
     * @param positionId the new position id
     * @throws DaoException
     */
    void updateUserPosition(String username, int positionId) throws DaoException;

    /**
     * update user trainer id
     *
     * @param username  the username
     * @param trainerId the id of the traier
     * @throws DaoException
     */
    void updateTrainerId(String username, int trainerId) throws DaoException;

    /**
     * update the state of the account activation
     *
     * @param username     the username
     * @param wasActivated was the account activated
     * @throws DaoException
     */
    void updateWasAccountActivated(String username, boolean wasActivated) throws DaoException;

    /**
     * sort all users
     *
     * @param fieldToCompare field by which to compare
     * @return the sorted usres
     * @throws DaoException
     */
    List<User> sortUsers(String fieldToCompare) throws DaoException;

    /**
     * sort users of the trainer
     *
     * @param trainerName    the name of the trainer
     * @param fieldToCompare the field by which to compare
     * @return sorted trainer customers
     * @throws DaoException
     */
    List<User> sortTrainerUsers(String trainerName, String fieldToCompare) throws DaoException;

    /**
     * was user email activated
     *
     * @param username the username
     * @return if user mail was activated
     * @throws DaoException
     */
    boolean wasUserMailActivated(String username) throws DaoException;
}
