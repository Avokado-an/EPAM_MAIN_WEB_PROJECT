package com.anton.gym.model.creator;

import com.anton.gym.model.entity.Department;

public class DepartmentCreator {
    public static final DepartmentCreator instance = new DepartmentCreator();

    private DepartmentCreator() {
    }

    public static DepartmentCreator getInstance() {
        return instance;
    }

    public Department createDepartment(
            String phoneNumber, String photoReference, String email, String city, String street, String house) {
        return new Department(phoneNumber, photoReference, email, city, street, house);
    }
}
