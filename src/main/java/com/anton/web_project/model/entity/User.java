package com.anton.web_project.model.entity;

import com.anton.web_project.model.entity.type.UserType;

public class User {
    private int id;
    private String username;
    private String password;
    private UserType type;
    private boolean isActive;

    public User(int id, String username, String password, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = UserType.CLIENT;
        this.isActive = isActive; //todo add activation later
    }

    public User(String username, String password, boolean isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive; //todo add activation later
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                isActive == user.isActive;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += username.hashCode() + password.hashCode() + Boolean.hashCode(isActive);
        return hash;
    }
}
