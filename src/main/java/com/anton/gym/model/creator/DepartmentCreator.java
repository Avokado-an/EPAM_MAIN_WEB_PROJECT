package com.anton.gym.model.creator;

import com.anton.gym.model.entity.Department;

/**
 * The {@code DepartmentCreator} class represents department creator.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class DepartmentCreator {
    public static final DepartmentCreator instance = new DepartmentCreator();

    private DepartmentCreator() {
    }

    /**
     * get instance
     *
     * @return the instance
     */
    public static DepartmentCreator getInstance() {
        return instance;
    }

    /**
     * creates object of Department class
     * @param phoneNumber the phone number
     * @param photoReference the reference to the photo of location
     * @param email the email
     * @param city the city
     * @param street the street
     * @param house the house
     * @return the Department object
     */
    public Department createDepartment(
            String phoneNumber, String photoReference, String email, String city, String street, String house) {
        return new Department(phoneNumber, photoReference, email, city, street, house);
    }
}
