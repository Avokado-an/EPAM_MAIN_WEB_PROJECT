package com.anton.web.model.entity;

public class Meal {
    private int id;
    private String name;
    private String description;
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;

    public Meal(int id, String name, String description, int calories, int proteins, int carbohydrates, int fats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Meal meal = (Meal) o;
        return getCalories() == meal.getCalories() &&
                getProteins() == meal.getProteins() &&
                getCarbohydrates() == meal.getCarbohydrates() &&
                getFats() == meal.getFats() &&
                getName().equals(meal.getName()) &&
                getDescription().equals(meal.getDescription());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += getName().hashCode();
        hashcode += getDescription().hashCode();
        hashcode += getCalories() * 31;
        hashcode += getProteins() * 31;
        hashcode += getCarbohydrates() * 31;
        hashcode += getFats() * 31;
        return hashcode;
    }
}
