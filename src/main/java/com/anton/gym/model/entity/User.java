package com.anton.gym.model.entity;

/**
 * The {@code User} class represents user.
 *
 * @author Anton Bogdanov
 * @version 1.0
 */
public class User {
    private String language;
    private int id;
    private String username;
    private UserType type;
    private String email;
    private boolean isActive;
    private String photoReference;
    private String description;

    /**
     * create the object of User class
     *
     * @param id             the id
     * @param username       the username
     * @param email          the email
     * @param type           the type
     * @param isActive       the activity
     * @param language       the language
     * @param photoReference the photo reference
     * @param description    the description
     */
    public User(int id, String username, String email, UserType type, boolean isActive, String language,
                String photoReference, String description) {
        this.description = description;
        this.photoReference = photoReference;
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
        this.isActive = isActive;
        this.language = language;
    }

    /**
     * create the object of User class
     *
     * @param username       the username
     * @param email          the email
     * @param type           the type
     * @param isActive       the activity
     * @param language       the language
     * @param photoReference the photo reference
     * @param description    the description
     */
    public User(String username, String email, UserType type, boolean isActive, String language,
                String photoReference, String description) {
        this.username = username;
        this.type = type;
        this.email = email;
        this.isActive = isActive;
        this.language = language;
        this.description = description;
        this.photoReference = photoReference;
    }

    /**
     * create the object of User class
     *
     * @param username the username
     * @param email    the email
     * @param isActive the activity
     */
    public User(String username, String email, boolean isActive) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
    }

    /**
     * create the object of User class
     *
     * @param username the username
     * @param isActive the activity
     */
    public User(String username, boolean isActive) {
        this.username = username;
        this.isActive = isActive;
    }

    /**
     * create the object of User class
     *
     * @param username       the username
     * @param photoReference the photo reference
     * @param description    the description
     */
    public User(String username, String description, String photoReference) {
        this.username = username;
        this.description = description;
        this.photoReference = photoReference;
    }

    /**
     * get photo reference
     *
     * @return the photo reference
     */
    public String getPhotoReference() {
        return photoReference;
    }

    /**
     * set [hoto reference
     *
     * @param photoReference the photo reference
     */
    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    /**
     * get description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get type
     *
     * @return the type
     */
    public UserType getType() {
        return type;
    }

    /**
     * set type
     *
     * @param type the type
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     * get username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get if user is active
     *
     * @return the activity
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * set activity
     *
     * @param active the activity
     */
    public void setActive(boolean active) {
        isActive = active;
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
     * get language
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * set the language
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.language = language;
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
                isActive == user.isActive &&
                getLanguage().equals(user.getLanguage()) &&
                getType().equals(user.getType()) &&
                getDescription().equals(user.getDescription()) &&
                getPhotoReference().equals(user.getPhotoReference()) &&
                getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int hash = 31;
        if (username != null) {
            hash += username.hashCode() + Boolean.hashCode(isActive);
        }
        hash += Boolean.hashCode(isActive);
        if (language != null) {
            hash += language.hashCode();
        }
        if (type != null) {
            hash += type.hashCode();
        }
        if (email != null) {
            hash += email.hashCode();
        }

        if (photoReference != null) {
            hash += photoReference.hashCode();
        }
        if (description != null) {
            hash += description.hashCode();
        }
        return hash;
    }
}
