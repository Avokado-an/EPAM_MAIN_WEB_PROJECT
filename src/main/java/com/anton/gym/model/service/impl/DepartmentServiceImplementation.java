package com.anton.gym.model.service.impl;

import com.anton.gym.exception.DaoException;
import com.anton.gym.exception.ServiceException;
import com.anton.gym.model.dao.DepartmentDao;
import com.anton.gym.model.dao.impl.DepartmentDaoImplementation;
import com.anton.gym.model.entity.Department;
import com.anton.gym.model.service.DepartmentService;

public class DepartmentServiceImplementation implements DepartmentService {
    private static final DepartmentService instance = new DepartmentServiceImplementation();

    private DepartmentServiceImplementation() {
    }

    public static DepartmentService getInstance() {
        return instance;
    }

    @Override
    public Department showDepartment() throws ServiceException {
        DepartmentDao dao = DepartmentDaoImplementation.getInstance();
        try {
            return dao.viewDepartmentInfo();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
