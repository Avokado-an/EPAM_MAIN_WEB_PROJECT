package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;

import java.math.BigDecimal;

/**
 * The {@code MembershipDao} class represents membership dao.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface MoneyAccountDao {
    /**
     * create user money account in db
     *
     * @param userId id of user account
     * @throws DaoException
     */
    void createUserMoneyAccount(int userId) throws DaoException;

    /**
     * change amount of money on user account
     *
     * @param userId              the id of account
     * @param moneyAmountToChange the amount of money by which to change
     * @throws DaoException
     */
    void changeMoneyAmount(int userId, BigDecimal moneyAmountToChange) throws DaoException;

    /**
     * show current user balance
     *
     * @param userId the id of account
     * @return amount of money on account
     * @throws DaoException
     */
    BigDecimal showCurrentBalance(int userId) throws DaoException;
}
