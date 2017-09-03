package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class UserActivity {

    private SimpleStringProperty username = new SimpleStringProperty("username");
    private SimpleObjectProperty<LocalDateTime> dateTime = new SimpleObjectProperty<>();

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(final String username) {
        this.username.set(username);
    }

    public LocalDateTime getDateTime() {
        return dateTime.get();
    }

    public SimpleObjectProperty<LocalDateTime> dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime.set(dateTime);
    }
}
