package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;

import java.math.BigDecimal;

public interface MoneyAccountDao {
    void createUserMoneyAccount(int userId) throws DaoException;

    void changeMoneyAmount(int userId, BigDecimal moneyAmountToChange) throws DaoException;

    BigDecimal showCurrentBalance(int userId) throws DaoException;
}
