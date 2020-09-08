package com.anton.web_project.model.entity;

public class Trainer {
    private int id;
    private String name;
    private double rating;
    private double price;

    public Trainer(int id, String name, double rating, double price) {
        this.id = id;
        this.name = name;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        Trainer trainer = (Trainer) o;
        return getId() == trainer.getId() &&
                Double.compare(trainer.getRating(), getRating()) == 0 &&
                Double.compare(trainer.getPrice(), getPrice()) == 0 &&
                getName().equals(trainer.getName());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += id * 31;
        hashcode += name.hashCode();
        hashcode += Double.hashCode(rating);
        hashcode += Double.hashCode(price);
        return hashcode;
    }
}
