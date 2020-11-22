package com.anton.gym.model.dao.query;

public class SqlDepartmentQuery {
    public static final String SELECT_DEPARTMENT = "SELECT department.phoneNumber, department.location_picture_reference, " +
            "department.email, address.city_name, address.street_name, address.house_number FROM department " +
            "INNER JOIN address ON address.id = department.address_id";
}
