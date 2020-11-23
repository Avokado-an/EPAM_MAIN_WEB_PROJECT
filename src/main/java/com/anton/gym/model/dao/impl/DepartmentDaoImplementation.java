package com.anton.gym.model.dao.impl;

import com.anton.gym.exception.ConnectionPoolException;
import com.anton.gym.exception.DaoException;
import com.anton.gym.model.creator.DepartmentCreator;
import com.anton.gym.model.dao.DepartmentDao;
import com.anton.gym.model.dao.pool.ConnectionPool;
import com.anton.gym.model.dao.query.SqlDepartmentQuery;
import com.anton.gym.model.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code DepartmentDaoImplementation} class represents department dao implementation.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class DepartmentDaoImplementation implements DepartmentDao {
    private static final DepartmentDao instance = new DepartmentDaoImplementation();

    private DepartmentDaoImplementation() {
    }

    /**
     * get instance of class
     * @return the instance
     */
    public static DepartmentDao getInstance() {
        return instance;
    }

    @Override
    public Department viewDepartmentInfo() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlDepartmentQuery.SELECT_DEPARTMENT)) {
            ResultSet resultSet = statement.executeQuery();
            return readDepartmentInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("Can't connect to db", ex);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from pool", e);
        }
    }

    private Department readDepartmentInfo(ResultSet resultSet) throws SQLException {
        DepartmentCreator creator = DepartmentCreator.getInstance();
        resultSet.next();
        String phoneNumber = resultSet.getString(1);
        String locationPictureReference = resultSet.getString(2);
        String email = resultSet.getString(3);
        String cityName = resultSet.getString(4);
        String streetName = resultSet.getString(5);
        String houseNumber = resultSet.getString(6);
        return creator.createDepartment(phoneNumber, locationPictureReference, email, cityName, streetName, houseNumber);
    }
}
