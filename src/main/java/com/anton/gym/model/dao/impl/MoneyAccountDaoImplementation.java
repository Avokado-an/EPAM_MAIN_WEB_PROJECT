package com.anton.gym.model.dao.impl;

import com.anton.gym.exception.ConnectionPoolException;
import com.anton.gym.exception.DaoException;
import com.anton.gym.model.dao.MoneyAccountDao;
import com.anton.gym.model.dao.pool.ConnectionPool;
import com.anton.gym.model.dao.query.SqlMoneyAccountQuery;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The {@code MoneyAccountDaoImplementation} class represents money account dao implementation.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class MoneyAccountDaoImplementation implements MoneyAccountDao {
    private static final BigDecimal USER_STARTING_MONEY_AMOUNT = new BigDecimal("0.");
    private static final MoneyAccountDaoImplementation instance = new MoneyAccountDaoImplementation();

    private MoneyAccountDaoImplementation() {
    }

    public static MoneyAccountDaoImplementation getInstance() {
        return instance;
    }

    @Override
    public void createUserMoneyAccount(int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlMoneyAccountQuery.ADD_MONEY_ACCOUNT)) {
            statement.setInt(1, userId);
            statement.setBigDecimal(2, USER_STARTING_MONEY_AMOUNT);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from pool", e);
        }
    }

    @Override
    public void changeMoneyAmount(int userId, BigDecimal moneyToSpend) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement selectMoneyAmountStatement = connection.prepareStatement(SqlMoneyAccountQuery.SELECT_MONEY_AMOUNT);
             PreparedStatement updateStatement = connection.prepareStatement(SqlMoneyAccountQuery.UPDATE_BALANCE)) {
            selectMoneyAmountStatement.setInt(1, userId);
            ResultSet resultSet = selectMoneyAmountStatement.executeQuery();
            resultSet.next();
            BigDecimal currentBalance = resultSet.getBigDecimal(1);
            List<BigDecimal> moneyBalances = Collections.singletonList(moneyToSpend);
            currentBalance = moneyBalances.stream().reduce(currentBalance, BigDecimal::add);
            updateStatement.setBigDecimal(1, currentBalance);
            updateStatement.setInt(2, userId);
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from pool", e);
        }
    }

    @Override
    public BigDecimal showCurrentBalance(int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement selectMoneyAmountStatement = connection.prepareStatement(SqlMoneyAccountQuery.SELECT_MONEY_AMOUNT)) {
            selectMoneyAmountStatement.setInt(1, userId);
            ResultSet resultSet = selectMoneyAmountStatement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(1);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from pool", e);
        }
    }
}
