package com.anton.gym.model.dao;

import com.anton.gym.model.entity.User;
import com.anton.gym.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user, String password, int languageId) throws DaoException;

    void remove(int id) throws DaoException;

    void updateActivity(User user) throws DaoException;

    void updateMembershipId(String username, int membershipId) throws DaoException;

    List<User> findAll() throws DaoException;

    Optional<User> find(String username, String password) throws DaoException;

    List<User> findAllTrainers() throws DaoException;

    List<User> findUserTrainers(String username) throws DaoException;

    List<User> findTrainerUsers(String trainerUsername) throws DaoException;

    Optional<User> findByName(String username) throws DaoException;

    void updateUsername(String oldName, String newName) throws DaoException;

    void updateDescription(String username, String description) throws DaoException;

    void updatePhotoReference(String username, String photoReference) throws DaoException;

    void updateUserPosition(String username, int positionId) throws DaoException;

    void updateTrainerId(String username, int trainerId) throws DaoException;

    void updateWasAccountActivated(String username, boolean wasActivated) throws DaoException;

    List<User> sortUsers(String fieldToCompare) throws DaoException;

    List<User> sortTrainerUsers(String trainerName, String fieldToCompare) throws DaoException;

    boolean wasUserMailActivated(String username) throws DaoException;
}
