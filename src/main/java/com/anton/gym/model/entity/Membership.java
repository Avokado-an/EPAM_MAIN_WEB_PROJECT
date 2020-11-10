package com.anton.gym.model.entity;

public class Membership {
    private int id;
    private String name;
    private String amountOfAttendees;
    private int months;
    private int price;

    public Membership(int id, String name, int price, String amountOfAttendees, int months) {
        this.id = id;
        this.name = name;
        this.amountOfAttendees = amountOfAttendees;
        this.price = price;
        this.months = months;
    }

    public Membership(String name, int price, String amountOfAttendees, int months) {
        this.name = name;
        this.amountOfAttendees = amountOfAttendees;
        this.price = price;
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountOfAttendees() {
        return amountOfAttendees;
    }

    public void setAmountOfAttendees(String amountOfAttendees) {
        this.amountOfAttendees = amountOfAttendees;
    }

    public int getPrice() {
        return price;
    }

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
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += name.hashCode();
        hashcode += amountOfAttendees.hashCode();
        hashcode += price * 31;
        hashcode += months * 31;
        return hashcode;
    }
}
