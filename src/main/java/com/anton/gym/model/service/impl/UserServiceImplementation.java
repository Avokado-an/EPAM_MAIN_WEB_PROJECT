package com.anton.gym.model.service.impl;

import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.exception.TransactionException;
import com.anton.gym.model.creator.UserCreator;
import com.anton.gym.model.dao.MembershipDao;
import com.anton.gym.model.dao.Transaction;
import com.anton.gym.model.dao.UserDao;
import com.anton.gym.model.dao.impl.MembershipDaoImplementation;
import com.anton.gym.model.dao.impl.UserDaoImplementation;
import com.anton.gym.model.entity.Membership;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MoneyAccountService;
import com.anton.gym.model.service.UserService;
import com.anton.gym.model.validator.UserValidator;
import com.anton.gym.util.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@code UserServiceImplementation} class represents user service implementation.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class UserServiceImplementation implements UserService {
    private static UserServiceImplementation instance = new UserServiceImplementation();

    public static UserServiceImplementation getInstance() {
        return instance;
    }

    private UserServiceImplementation() {

    }

    @Override
    public boolean isUsernameTaken(String username) throws ServiceException {
        try {
            UserDao dao = UserDaoImplementation.getInstance();
            Optional<User> userFromDb = dao.findByName(username);
            return userFromDb.isPresent();
        } catch (DaoException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean register(String username, String password, String email, int languageId) throws ServiceException {
        UserValidator userValidator = UserValidator.getInstance();
        Transaction transaction = Transaction.getInstance();
        boolean isActive = false;
        boolean registrationResult = false;
        Optional<User> userToAdd = UserCreator.getInstance().createUser(username, email, isActive);
        String encodedPassword = PasswordEncoder.encodeString(password);
        try {
            if (userToAdd.isPresent() && userValidator.validateStringCharacters(password)) {
                transaction.createUserAndMoneyAccount(userToAdd.get(), encodedPassword, languageId);
                registrationResult = true;
            }
        } catch (TransactionException ex) {
            throw new ServiceException("Can't save user", ex);
        }
        return registrationResult;
    }

    @Override
    public Optional<User> logIn(String username, String password) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        String codedPassword = PasswordEncoder.encodeString(password);
        try {
            return dao.find(username, codedPassword);
        } catch (DaoException ex) {
            throw new ServiceException("Can't login", ex);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            return dao.findByName(username);
        } catch (DaoException ex) {
            throw new ServiceException("Can't login", ex);
        }
    }

    @Override
    public boolean updateUsername(String oldName, String newName) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        UserValidator userValidator = UserValidator.getInstance();
        boolean wasUsernameUpdated = true;
        try {
            if (userValidator.validateStringCharacters(newName)) {
                userDao.updateUsername(oldName, newName);
            } else {
                wasUsernameUpdated = false;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasUsernameUpdated;
    }

    @Override
    public boolean updateDescription(String username, String description) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        UserValidator userValidator = UserValidator.getInstance();
        boolean wasDescriptionUpdated = true;
        try {
            if (userValidator.validateDescription(description)) {
                userDao.updateDescription(username, description);
            } else {
                wasDescriptionUpdated = false;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasDescriptionUpdated;
    }

    @Override
    public boolean updatePhotoReference(String username, String photoReference) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        UserValidator userValidator = UserValidator.getInstance();
        boolean wasPhotoReferenceUpdated = true;
        try {
            if (userValidator.validatePhotoReference(photoReference)) {
                userDao.updatePhotoReference(username, photoReference);
            } else {
                wasPhotoReferenceUpdated = false;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasPhotoReferenceUpdated;
    }

    @Override
    public boolean updateTrainerId(String username, String trainerName) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        boolean wasTrainerIdUpdated = true;
        try {
            Optional<User> trainer = findByUsername(trainerName);
            if (trainer.isPresent()) {
                int id = trainer.get().getId();
                userDao.updateTrainerId(username, id);
            } else {
                wasTrainerIdUpdated = false;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasTrainerIdUpdated;
    }

    @Override
    public List<User> findAllTrainers() throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> trainers;
        try {
            trainers = userDao.findAllTrainers();
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return trainers;
    }

    @Override
    public List<User> findTrainerUserByUsername(String trainerName, String userToFind) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            List<User> allUsers = dao.findTrainerUsers(trainerName);
            List<User> searchedUsers = new ArrayList<>();
            allUsers.stream().filter(user -> user.getUsername().contains(userToFind)).forEach(searchedUsers::add);
            return searchedUsers;
        } catch (DaoException e) {
            throw new ServiceException("can't view all users", e);
        }
    }

    @Override
    public List<User> findUserTrainers(String username) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> trainers;
        try {
            trainers = userDao.findUserTrainers(username);
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return trainers;
    }

    @Override
    public List<User> findTrainerUsers(String trainerUsername) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        List<User> users;
        try {
            users = userDao.findTrainerUsers(trainerUsername);
        } catch (DaoException ex) {
            throw new ServiceException("Can't find all trainers", ex);
        }
        return users;
    }

    @Override
    public List<User> sortTrainerUsers(String trainerName, String fieldToCompare) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            return dao.sortTrainerUsers(trainerName, fieldToCompare);
        } catch (DaoException e) {
            throw new ServiceException("can't view all users", e);
        }
    }

    @Override
    public boolean wasUserMailActivated(String username) throws ServiceException {
        UserDao dao = UserDaoImplementation.getInstance();
        try {
            return dao.wasUserMailActivated(username);
        } catch (DaoException e) {
            throw new ServiceException("can't view all users", e);
        }
    }

    @Override
    public void updateWasAccountActivated(String username, boolean wasActivated) throws ServiceException {
        UserDao userDao = UserDaoImplementation.getInstance();
        try {
            userDao.updateWasAccountActivated(username, wasActivated);
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
    }

    @Override
    public boolean updateMembershipId(String username, String membershipId) throws ServiceException {
        boolean wasMembershipIdUpdated;
        try {
            int id = Integer.parseInt(membershipId);
            wasMembershipIdUpdated = purchaseMembership(id, username);
        } catch (NumberFormatException e) {
            wasMembershipIdUpdated = false;
        } catch (DaoException | TransactionException ex) {
            throw new ServiceException("Can't purchase membership", ex);
        }
        return wasMembershipIdUpdated;
    }

    private boolean purchaseMembership(int id, String username) throws DaoException, ServiceException, TransactionException {
        UserDao userDao = UserDaoImplementation.getInstance();
        MembershipDao membershipDao = MembershipDaoImplementation.getInstance();
        Optional<Membership> membership = membershipDao.findById(id);
        Optional<User> user = userDao.findByName(username);
        boolean wasMembershipIdUpdated = false;
        if (membership.isPresent() && user.isPresent()) {
            wasMembershipIdUpdated = addMembershipToUser(user.get(), membership.get(), id, username);
        }
        return wasMembershipIdUpdated;
    }

    private boolean addMembershipToUser(User user, Membership membership, int id, String username)
            throws TransactionException, ServiceException {
        Transaction transaction = Transaction.getInstance();
        MoneyAccountService moneyAccountDao = MoneyAccountServiceImplementation.getInstance();
        boolean wasMembershipIdUpdated = false;
        if (moneyAccountDao.doesHaveEnoughMoney(user.getId(), membership.getPrice())) {
            transaction.purchaseMembership(id, membership.getPrice(), user.getId(), username);
            wasMembershipIdUpdated = true;
        }
        return wasMembershipIdUpdated;
    }
}
