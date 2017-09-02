package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.SimpleStringProperty;

public class Comment extends UserActivity {

    private SimpleStringProperty message = new SimpleStringProperty( "message");

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
