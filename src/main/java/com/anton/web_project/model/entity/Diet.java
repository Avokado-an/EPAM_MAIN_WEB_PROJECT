package com.anton.web_project.model.entity;

import java.util.Collections;
import java.util.List;

public class Diet {
    private int id;
    private String name;
    private String description;
    private List<Meal> meals; //todo do Observer for total carbs and stuff
    private int totalCalories;
    private int totalProteins;
    private int totalCarbohydrates;
    private int totalFats;

    public Diet(int id, String name, String description, List<Meal> meals) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.meals = meals;
        totalCalories = calculateCalories();//todo observer
        totalCarbohydrates = calculateCarbohydrates();
        totalFats = calculateFats();
        totalProteins = calculateProteins();
    }

    public List<Meal> getMeals() {
        return Collections.unmodifiableList(meals);
    }

    public void setMeals(List<Meal> meals) {
        if (meals != null) {
            this.meals = meals;
        }
        totalCalories = calculateCalories();//todo observer
        totalCarbohydrates = calculateCarbohydrates();
        totalFats = calculateFats();
        totalProteins = calculateProteins();
    }

    public void addMeal(Meal meal) {
        if (meals != null) {
            meals.add(meal);
        }
        totalCalories = calculateCalories();//todo observer
        totalCarbohydrates = calculateCarbohydrates();
        totalFats = calculateFats();
        totalProteins = calculateProteins();
    }

    public void removeMeal(Meal meal) {
        if(meals.contains(meal)) {
            meals.remove(meal);
        }
        totalCalories = calculateCalories();//todo observer
        totalCarbohydrates = calculateCarbohydrates();
        totalFats = calculateFats();
        totalProteins = calculateProteins();
    }

    public void removeMeal(int id) {
        if(id > 0 && id < meals.size()) {
            meals.remove(id);
        }
        totalCalories = calculateCalories();//todo observer
        totalCarbohydrates = calculateCarbohydrates();
        totalFats = calculateFats();
        totalProteins = calculateProteins();
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

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getTotalProteins() {
        return totalProteins;
    }

    public int getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public int getTotalFats() {
        return totalFats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diet diet = (Diet) o;
        return getId() == diet.getId() &&
                getName().equals(diet.getName()) &&
                getDescription().equals(diet.getDescription());
    }

    @Override
    public int hashCode() {
        int hashcode = 17;
        hashcode += id * 31;
        hashcode += name.hashCode();
        hashcode += description.hashCode();
        return hashcode;
    }

    private int calculateCalories() { //todo get rid of this methods and create observer
        return meals.stream().mapToInt(Meal::getCalories).sum();
    }

    private int calculateProteins() { //todo get rid of this methods and create observer
        return meals.stream().mapToInt(Meal::getProteins).sum();
    }

    private int calculateCarbohydrates() { //todo get rid of this methods and create observer
        return meals.stream().mapToInt(Meal::getCarbohydrates).sum();
    }

    private int calculateFats() { //todo get rid of this methods and create observer
        return meals.stream().mapToInt(Meal::getFats).sum();
    }
}
