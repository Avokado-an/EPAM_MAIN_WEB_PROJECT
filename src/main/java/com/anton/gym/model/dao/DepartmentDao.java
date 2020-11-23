package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;
import com.anton.gym.model.entity.Department;

/**
 * The {@code DepartmentDao} class represents department dao.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface DepartmentDao {
    /**
     * view Department info
     * @return the department
     * @throws DaoException
     */
    Department viewDepartmentInfo() throws DaoException;
}
