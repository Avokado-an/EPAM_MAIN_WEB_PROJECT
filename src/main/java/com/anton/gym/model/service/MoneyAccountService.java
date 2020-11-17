package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;

import java.math.BigDecimal;

/**
 * The {@code MoneyAccountService} class represents money account service.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface MoneyAccountService {
    /**
     * increase amount of money on account
     *
     * @param username   the username
     * @param moneyAdded the amount of money added
     * @return was operation successful
     * @throws ServiceException
     */
    boolean increaseMoneyOnAccount(String username, String moneyAdded) throws ServiceException;

    /**
     * check amount of money on account
     *
     * @param username the name of the user
     * @return the amount of money
     * @throws ServiceException
     */
    BigDecimal checkMoneyOnAccount(String username) throws ServiceException;

    /**
     * check if user has enough money for operation
     *
     * @param userId the id of account
     * @param money  the amount of money
     * @return if user has enough money
     * @throws ServiceException
     */
    boolean doesHaveEnoughMoney(int userId, double money) throws ServiceException;
}
