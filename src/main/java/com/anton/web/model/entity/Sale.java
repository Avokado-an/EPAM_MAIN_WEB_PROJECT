package com.anton.web.model.entity;

public class Sale {
    private int id;
    private String description;
    private double percentage;

    public Sale(int id, String description, double percentage) {
        this.id = id;
        this.description = description;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sale sale = (Sale) o;
        return getId() == sale.getId() &&
                Double.compare(sale.getPercentage(), getPercentage()) == 0 &&
                getDescription().equals(sale.getDescription());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += id * 31;
        hashcode += description.hashCode();
        hashcode += Double.hashCode(percentage);
        return hashcode;
    }
}
