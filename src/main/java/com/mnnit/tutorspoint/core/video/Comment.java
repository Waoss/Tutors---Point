package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Comment extends UserActivity {
    private StringProperty message = new SimpleStringProperty(this, "message");

    public String getMessage() {
        return message.get();
    }

    public void setMessage(final String message) {
        this.message.set(message);
    }

    public StringProperty messageProperty() {
        return message;
    }
}
