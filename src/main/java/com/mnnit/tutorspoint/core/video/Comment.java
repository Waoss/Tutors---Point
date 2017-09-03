package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Comment extends UserActivity {

    public Comment(final String message, final String username) {
        this.message.set(message);
        this.usernameProperty().set(username);
        this.dateTimeProperty().set(LocalDateTime.now());
    }

    private SimpleStringProperty message = new SimpleStringProperty("message");

    public String getMessage() {
        return message.get();
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void setMessage(final String message) {
        this.message.set(message);
    }
}
