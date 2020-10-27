package com.anton.web.model.dao;

import com.anton.web.model.entity.User;
import com.anton.web.model.exception.DaoException;
import com.anton.web.model.exception.ServiceException;

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
}
