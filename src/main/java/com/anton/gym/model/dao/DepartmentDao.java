package com.anton.gym.model.dao;

import com.anton.gym.exception.DaoException;
import com.anton.gym.model.entity.Department;

public interface DepartmentDao {
    Department viewDepartmentInfo() throws DaoException;
}
