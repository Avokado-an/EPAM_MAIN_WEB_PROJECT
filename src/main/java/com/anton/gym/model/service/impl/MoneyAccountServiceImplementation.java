package com.anton.gym.model.service.impl;

import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.dao.MoneyAccountDao;
import com.anton.gym.model.dao.UserDao;
import com.anton.gym.model.dao.impl.MoneyAccountDaoImplementation;
import com.anton.gym.model.dao.impl.UserDaoImplementation;
import com.anton.gym.model.entity.User;
import com.anton.gym.model.service.MoneyAccountService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * The {@code MoneyAccountServiceImplementation} class represents money account service implementation.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MoneyAccountServiceImplementation implements MoneyAccountService {
    private static MoneyAccountServiceImplementation instance;
    private static final int MAX_AMOUNT_TO_ADD = 2000;

    private MoneyAccountServiceImplementation() {
    }

    public static MoneyAccountServiceImplementation getInstance() {
        if (instance == null) {
            instance = new MoneyAccountServiceImplementation();
        }
        return instance;
    }

    @Override
    public BigDecimal checkMoneyOnAccount(String username) throws ServiceException {
        MoneyAccountDao moneyDao = MoneyAccountDaoImplementation.getInstance();
        UserDao userDao = UserDaoImplementation.getInstance();
        BigDecimal balance = BigDecimal.ZERO;
        try {
            Optional<User> user = userDao.findByName(username);
            if (user.isPresent()) {
                balance = moneyDao.showCurrentBalance(user.get().getId());
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return balance;
    }

    @Override
    public boolean doesHaveEnoughMoney(int userId, double money) throws ServiceException {
        MoneyAccountDao moneyDao = MoneyAccountDaoImplementation.getInstance();
        boolean doesHaveEnoughMoney = false;
        try {
            BigDecimal currentBalance = moneyDao.showCurrentBalance(userId);
            if (currentBalance.compareTo(BigDecimal.valueOf(money)) >= 0) {
                doesHaveEnoughMoney = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return doesHaveEnoughMoney;
    }

    @Override
    public boolean increaseMoneyOnAccount(String username, String moneyAdded) throws ServiceException {
        boolean wasOperationSuccessful = false;
        try {
            double moneyValue = Double.parseDouble(moneyAdded);
            if (moneyValue >= 0 && moneyValue < MAX_AMOUNT_TO_ADD) {
                wasOperationSuccessful = increaseBalance(username, moneyValue);
            }
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong number format when adding money: ", e);
        }
        return wasOperationSuccessful;
    }

    private boolean increaseBalance(String username, double moneyValue) throws ServiceException {
        MoneyAccountDao moneyDao = MoneyAccountDaoImplementation.getInstance();
        UserDao userDao = UserDaoImplementation.getInstance();
        boolean wasOperationSuccessful = false;
        try {
            Optional<User> user = userDao.findByName(username);
            if (user.isPresent()) {
                BigDecimal money = BigDecimal.valueOf(moneyValue);
                moneyDao.changeMoneyAmount(user.get().getId(), money);
                wasOperationSuccessful = true;
            }
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return wasOperationSuccessful;
    }
}
