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

public class MoneyAccountServiceImplementation implements MoneyAccountService {
    private static MoneyAccountServiceImplementation instance;

    private MoneyAccountServiceImplementation() {
    }

    public static MoneyAccountServiceImplementation getInstance() {
        if (instance == null) {
            instance = new MoneyAccountServiceImplementation();
        }
        return instance;
    }

    @Override
    public boolean reduceMoneyOnAccount(int userId, double moneySpent) throws ServiceException {
        MoneyAccountDao moneyDao = MoneyAccountDaoImplementation.getInstance();
        boolean wasOperationSuccessful = false;
        if (moneySpent >= 0) {
            try {
                moneySpent *= -1;
                BigDecimal money = BigDecimal.valueOf(moneySpent);
                moneyDao.changeMoneyAmount(userId, money);
                wasOperationSuccessful = true;
            } catch (DaoException ex) {
                throw new ServiceException("Can't change users membership_id", ex);
            }
        }
        return wasOperationSuccessful;
    }

    @Override
    public BigDecimal checkMoneyOnAccount(int userId) throws ServiceException {
        MoneyAccountDao moneyDao = MoneyAccountDaoImplementation.getInstance();
        BigDecimal balance;
        try {
            balance = moneyDao.showCurrentBalance(userId);
        } catch (DaoException ex) {
            throw new ServiceException("Can't change users membership_id", ex);
        }
        return balance;
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
            if (moneyValue >= 0) {
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
