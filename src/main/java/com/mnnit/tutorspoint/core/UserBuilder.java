package com.mnnit.tutorspoint.core;

/**
 * A builder class for the {@link User} class.
 * <br>
 * A user can be built with {@link #createUser()} method after calling {@link #setUsername(String)} or/and {@link
 * #setPassword(String)}
 * <p>
 * For example, <p>{@code
 * User user = new UserBuilder().setUsername("foo").setPassword("bar").createUser();
 * }</p>
 */
public class UserBuilder {
    /**
     * The username of the user
     */
    private String username;
    /**
     * The password of the user
     */
    private String password;
    private UserType userType;

    /**
     * Sets the username of the user
     *
     * @param username
     *         The new username
     * @return itself; confining to the builder standard
     */
    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * Sets the password of the user
     *
     * @param password
     *         The new password
     * @return itself; confining to the builder standard
     */
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Returns a new user with the set username and password.
     *
     * @return a new user with the set username and password.
     */
    public User createUser() {
        return new User(username, password, userType);
    }

    public UserBuilder setUserType(final UserType userType) {
        this.userType = userType;
        return this;
    }
}