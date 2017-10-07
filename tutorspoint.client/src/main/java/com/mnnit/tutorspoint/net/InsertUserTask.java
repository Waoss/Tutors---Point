package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.User;

import java.io.IOException;
import java.net.URL;

public class InsertUserTask extends BooleanResponseTask {
    public InsertUserTask(final User user) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                + "/insertUser?username=" + user.getUsername()
                + "&password=" + user.getPassword()
                + "&usertype=" + user.getUserType().toString()));
    }
}
