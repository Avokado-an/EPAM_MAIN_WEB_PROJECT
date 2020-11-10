package com.anton.gym.model.dao;

import com.anton.gym.exception.ConnectionPoolException;
import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.TransactionException;
import com.anton.gym.model.dao.impl.MoneyAccountDaoImplementation;
import com.anton.gym.model.dao.impl.UserDaoImplementation;
import com.anton.gym.model.dao.pool.ConnectionPool;
import com.anton.gym.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private static Transaction instance;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NEGATIVE = -1;

    private Transaction() {

    }

    public static Transaction getInstance() {
        if (instance == null) {
            instance = new Transaction();
        }
        return instance;
    }

    public void createUserAndMoneyAccount(User user, String password, int languageId) throws TransactionException {
        UserDao userDao = UserDaoImplementation.getInstance();
        MoneyAccountDao moneyAccountDao = MoneyAccountDaoImplementation.getInstance();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            userDao.save(user, password, languageId);
            moneyAccountDao.createUserMoneyAccount(user.getId());
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't add user and his money account", e);
        } finally {
            closeConnection(connection);
        }
    }

    public void purchaseMembership(int membershipId, double membershipPrice, int userId, String username)
            throws TransactionException {
        UserDao userDao = UserDaoImplementation.getInstance();
        MoneyAccountDao moneyAccountDao = MoneyAccountDaoImplementation.getInstance();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            BigDecimal price = BigDecimal.valueOf(membershipPrice * NEGATIVE);
            moneyAccountDao.changeMoneyAmount(userId, price);
            userDao.updateMembershipId(username, membershipId);
        } catch (ConnectionPoolException | SQLException | DaoException e) {
            rollbackConnection(connection);
            throw new TransactionException("Can't add user and his money account", e);
        } finally {
            closeConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            LOGGER.error("Error while rollback transaction: ", e);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error while closing connection: ", e);
            }
        }
    }
}
