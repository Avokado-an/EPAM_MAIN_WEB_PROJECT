package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Department;

public interface DepartmentService {
    Department showDepartment() throws ServiceException;
}
