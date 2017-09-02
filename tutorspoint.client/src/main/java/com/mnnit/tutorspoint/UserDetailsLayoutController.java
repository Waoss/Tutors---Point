package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class UserDetailsLayoutController implements Initializable {
    public static final Logger LOGGER = Logger.getLogger(UserDetailsLayoutController.class.getName());
    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>(this, "user");

    public User getUser() {
        return user.get();
    }

    public void setUser(final User user) {
        this.user.set(user);
    }

    public final SimpleObjectProperty<User> userProperty() {
        return user;
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        user.addListener((observable, oldValue, newValue) -> {
            LOGGER.info("The user has changed to " + newValue.getUsername());
        });
    }
}
