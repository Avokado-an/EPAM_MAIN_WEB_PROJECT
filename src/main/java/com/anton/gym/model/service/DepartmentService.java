package com.anton.gym.model.service;

import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.entity.Department;

/**
 * The {@code DepartmentService} class represents department service.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public interface DepartmentService {
    /**
     * show the department
     * @return the department
     * @throws ServiceException
     */
    Department showDepartment() throws ServiceException;
}
