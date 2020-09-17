package com.anton.web_project.model.entity;

import com.anton.web_project.model.entity.type.UserType;

public class User {
    private int id;
    private String username;
    private UserType type;
    private String email;
    private boolean isActive;

    public User(int id, String username, String email, UserType type, boolean isActive) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.isActive = isActive; //todo add activation later
    }

    public User(String username, String email, UserType type, boolean isActive) {
        this.username = username;
        this.type = type;
        this.email = email;
        this.isActive = isActive; //todo add activation later
    }

    public User(String username, String email, boolean isActive) {
        this.username = username;
        this.email = email;
        this.isActive = isActive; //todo add activation later
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return getUsername().equals(user.getUsername()) &&//todo specify when finished with other entity fields
                isActive == user.isActive;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash += username.hashCode() + Boolean.hashCode(isActive);//todo specify everything later
        return hash;
    }
}
