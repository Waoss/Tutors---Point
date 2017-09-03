package com.mnnit.tutorspoint.core.video;

import java.time.ZonedDateTime;

public class Comment extends UserActivity {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Comment(final String username, final String message) {
        setUsername(username);
        setMessage(message);
        setDateTime(ZonedDateTime.now().toString());
    }
}
