package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.User;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class GetUserTask extends GenericResponsiveTask<User[]> {
    User[] users;

    public GetUserTask(final String username) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                + "/getLoggedUsers?username=" + username), User[].class);
    }

    @Override
    protected User[] call() throws Exception {
        return users = super.call();
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(final User[] users) {
        this.users = users;
    }

    public boolean isEmpty() {
        List<User> userList = Arrays.asList(users);
        return userList.isEmpty();
    }
}
