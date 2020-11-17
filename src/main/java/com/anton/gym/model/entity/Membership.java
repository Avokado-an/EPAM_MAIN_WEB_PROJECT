package com.anton.gym.model.entity;

/**
 * The {@code Membership} class represents membership.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class Membership {
    private int id;
    private String name;
    private String amountOfAttendees;
    private int months;
    private int price;
    private boolean isActive;

    /**
     * create object of class
     *
     * @param id                the id
     * @param name              the name
     * @param price             the price
     * @param amountOfAttendees the amount of attendees
     * @param months            the months amount
     * @param isActive          is membership currently active
     */
    public Membership(int id, String name, int price, String amountOfAttendees, int months, boolean isActive) {
        this.id = id;
        this.name = name;
        this.amountOfAttendees = amountOfAttendees;
        this.price = price;
        this.months = months;
        this.isActive = isActive;
    }

    /**
     * create object of class
     *
     * @param name              the name
     * @param price             the price
     * @param amountOfAttendees the amount of attendees
     * @param months            the months amount
     * @param isActive          is membership currently active
     */
    public Membership(String name, int price, String amountOfAttendees, int months, boolean isActive) {
        this.name = name;
        this.amountOfAttendees = amountOfAttendees;
        this.price = price;
        this.months = months;
        this.isActive = isActive;
    }

    /**
     * return is active
     *
     * @return is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * set activity
     *
     * @param active is active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * get amount of months
     *
     * @return the months
     */
    public int getMonths() {
        return months;
    }

    /**
     * set amount of months
     *
     * @param months amount of months
     */
    public void setMonths(int months) {
        this.months = months;
    }

    /**
     * get id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * set id
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get amount of attendees
     *
     * @return the amount of attendees
     */
    public String getAmountOfAttendees() {
        return amountOfAttendees;
    }

    /**
     * set amount of attendees
     *
     * @param amountOfAttendees the amount of attendees
     */
    public void setAmountOfAttendees(String amountOfAttendees) {
        this.amountOfAttendees = amountOfAttendees;
    }

    /**
     * get price
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * set the price
     *
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Membership that = (Membership) o;
        return getAmountOfAttendees().equals(that.getAmountOfAttendees()) &&
                getMonths() == that.getMonths() &&
                getPrice() == that.getPrice() &&
                getName().equals(that.getName()) &&
                isActive() == that.isActive();
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += name.hashCode();
        hashcode += amountOfAttendees.hashCode();
        hashcode += price * 31;
        hashcode += months * 31;
        hashcode += (isActive ? 1 : 0) * 31;
        return hashcode;
    }
}
