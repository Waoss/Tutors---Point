package com.mnnit.tutorspoint.core.video;

import com.mnnit.tutorspoint.core.User;
import javafx.beans.property.SimpleObjectProperty;

import java.time.ZonedDateTime;

public class UserActivity {
    private SimpleObjectProperty<User> user = new SimpleObjectProperty<>(this, "user");
    private SimpleObjectProperty<ZonedDateTime> dateTime = new SimpleObjectProperty<>(this, "dateTime");

    public User getUser() {
        return user.get();
    }

    public void setUser(final User user) {
        this.user.set(user);
    }

    public SimpleObjectProperty<User> userProperty() {
        return user;
    }

    public ZonedDateTime getDateTime() {
        return dateTime.get();
    }

    public void setDateTime(final ZonedDateTime dateTime) {
        this.dateTime.set(dateTime);
    }

    public SimpleObjectProperty<ZonedDateTime> dateTimeProperty() {
        return dateTime;
    }
}
