package com.anton.web_project.model.entity;

public class Diet {
    private int id;
    private String name;
    private String description;

    public Diet(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
