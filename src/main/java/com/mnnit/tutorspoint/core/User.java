package com.mnnit.tutorspoint.core;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a user.<br>
 * A user is simply a container for a username and password.<br>
 * The recommended way of building user objects is using the {@link UserBuilder} class.
 * <p>A user can be constructed using {@link UserBuilder} or using the {@link #User(String, String, UserType)}
 * constructor.</p>
 * For example, <p><br>{@code
 * User user = new User("foo", "bar");
 * user.setPassword("dasd"); // or whatever
 * }<br></p>
 *
 * @see UserBuilder
 */
public class User {

    private SimpleObjectProperty<UserType> userType = new SimpleObjectProperty<>();
    /**
     * The username of the user
     */
    private SimpleStringProperty username = new SimpleStringProperty();
    /**
     * The password of the user
     */
    private transient SimpleStringProperty password = new SimpleStringProperty();

    /**
     * Creates a new user by the given username and password
     *
     * @param username
     *         The username
     * @param password
     *         The password
     * @param userType
     *         The usertype
     */
    public User(String username, String password, UserType userType) {
        this.username.set(username);
        this.password.set(password);
        this.userType.set(userType);
    }

    /**
     * Returns the property that contains the username
     *
     * @return the property that contains the username
     */
    public final SimpleStringProperty usernameProperty() {
        return username;
    }

    /**
     * Returns the property that contains the password
     *
     * @return the property that contains the password
     */
    public final SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleObjectProperty<UserType> userTypeProperty() {
        return userType;
    }

    @Override
    public int hashCode() {
        int result = getUserType() != null ? getUserType().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User user = (User) o;

        if (getUserType() != null ? !getUserType().equals(user.getUserType()) : user.getUserType() != null) {
            return false;
        }
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null) {
            return false;
        }
        return getPassword() != null ? getPassword().equals(user.getPassword()) : user.getPassword() == null;
    }

    public UserType getUserType() {
        return userType.get();
    }

    /**
     * Returns the username of the user
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Sets the value of the username propery
     *
     * @param username
     *         The value to set
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * Returns the password
     *
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Sets the password to a new value
     *
     * @param password
     *         the new value
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setUserType(final UserType userType) {
        this.userType.set(userType);
    }
}
