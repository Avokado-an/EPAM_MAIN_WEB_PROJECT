package com.anton.web_project.model.entity;

public class Membership {
    private int id;
    private String name;
    private int amountOfAttendees;
    private int price;

    public Membership(int id, String name, int amountOfAttendees, int price) {
        this.id = id;
        this.name = name;
        this.amountOfAttendees = amountOfAttendees;
        this.price = price;
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

    public int getAmountOfAttendees() {
        return amountOfAttendees;
    }

    public void setAmountOfAttendees(int amountOfAttendees) {
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
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Membership that = (Membership) o;
        return getId() == that.getId() &&
                getAmountOfAttendees() == that.getAmountOfAttendees() &&
                getPrice() == that.getPrice() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += id * 31;
        hashcode += name.hashCode();
        hashcode += amountOfAttendees * 31;
        hashcode += price * 31;
        return hashcode;
    }
}
