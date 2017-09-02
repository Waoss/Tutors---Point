package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZonedDateTime;

public class UserActivity {

    private SimpleStringProperty username = new SimpleStringProperty("username");
    private SimpleObjectProperty<ZonedDateTime> dateTime = new SimpleObjectProperty<>();

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(final String username) {
        this.username.set(username);
    }

    public ZonedDateTime getDateTime() {
        return dateTime.get();
    }

    public SimpleObjectProperty<ZonedDateTime> dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(final ZonedDateTime dateTime) {
        this.dateTime.set(dateTime);
    }
}
