package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;

import java.math.BigDecimal;

public interface MoneyAccountService {
    boolean reduceMoneyOnAccount(int userId, double moneySpent) throws ServiceException;

    boolean increaseMoneyOnAccount(String username, String moneyAdded) throws ServiceException;

    BigDecimal checkMoneyOnAccount(int userId) throws ServiceException;

    BigDecimal checkMoneyOnAccount(String username) throws ServiceException;

    boolean doesHaveEnoughMoney(int userId, double money) throws ServiceException;
}
