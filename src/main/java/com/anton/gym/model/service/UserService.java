package com.anton.gym.model.service;

import com.anton.gym.exception.TransactionException;
import com.anton.gym.model.entity.User;
import com.anton.gym.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isUsernameTaken(String username) throws ServiceException;

    boolean register(String username, String password, String email, int languageId) throws ServiceException, TransactionException;

    Optional<User> logIn(String username, String password) throws ServiceException;

    Optional<User> findByUsername(String username) throws ServiceException;

    boolean updateMembershipId(String username, String membershipId) throws ServiceException, TransactionException;

    Optional<String> defineUserLanguage(String username) throws ServiceException;

    boolean updateUsername(String oldName, String newName) throws ServiceException;

    boolean updateDescription(String username, String description) throws ServiceException;

    boolean updatePhotoReference(String username, String photoReference) throws ServiceException;

    boolean updateTrainerId(String username, String trainerName) throws ServiceException;

    void updateWasAccountActivated(String username, boolean wasActivated) throws ServiceException;

    List<User> findAllTrainers() throws ServiceException;

    List<User> findUserTrainers(String username) throws ServiceException;

    List<User> findTrainerUsers(String trainerUsername) throws ServiceException;

    List<User> sortTrainerUsers(String trainerName, String fieldToCompare) throws ServiceException;//todo Service for trainers

    List<User> findTrainerUserByUsername(String trainerName, String userToFind) throws ServiceException;

    boolean wasUserMailActivated(String username) throws ServiceException;
}
