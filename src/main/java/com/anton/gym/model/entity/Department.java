package com.anton.gym.model.entity;

/**
 * The {@code Department} class represents department.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class Department {
    private String phoneNumber;
    private String photoReference;
    private String email;
    private String city;
    private String street;
    private String house;

    /**
     * creates object of class
     *
     * @param phoneNumber    the phone number
     * @param photoReference the location photo reference
     * @param email          the email
     * @param city           the city
     * @param street         the street
     * @param house          the house
     */
    public Department(String phoneNumber, String photoReference, String email, String city, String street, String house) {
        this.phoneNumber = phoneNumber;
        this.photoReference = photoReference;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    /**
     * get phone number
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set the phone number
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * get reference to the photo
     *
     * @return the photo reference
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     * set photo reference
     *
     * @param photoReference the photo reference
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    /**
     * get email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get the city
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * set the city
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * get the street
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * set the street
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * get the house
     *
     * @return the house
     */
    public String getHouse() {
        return house;
    }

    /**
     * set the house
     *
     * @param house the house
     */
    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return getPhoneNumber().equals(that.getPhoneNumber()) &&
                getPhotoReference().equals(that.getPhotoReference()) &&
                getEmail().equals(that.getEmail()) &&
                getCity().equals(that.getCity()) &&
                getStreet().equals(that.getStreet()) &&
                getHouse().equals(that.getHouse());
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash += getCity().hashCode();
        hash += getEmail().hashCode();
        hash += getHouse().hashCode();
        hash += getPhoneNumber().hashCode();
        hash += getPhotoReference().hashCode();
        hash += getStreet().hashCode();
        return hash;
    }
}
