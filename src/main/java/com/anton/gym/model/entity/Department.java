package com.anton.gym.model.entity;

public class Department {
    private String phoneNumber;
    private String photoReference;
    private String email;
    private String city;
    private String street;
    private String house;

    public Department(String phoneNumber, String photoReference, String email, String city, String street, String house) {
        this.phoneNumber = phoneNumber;
        this.photoReference = photoReference;
        this.email = email;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

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
