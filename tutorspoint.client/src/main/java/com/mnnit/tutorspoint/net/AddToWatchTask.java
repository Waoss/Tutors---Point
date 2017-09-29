package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.todo.Todo;

import java.io.IOException;
import java.net.URL;

public class AddToWatchTask extends NoResponseTask {

    public AddToWatchTask(final Todo todo) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/insertTodo?student=" +
                todo.getStudent() + "&message=" + todo.getMessage()));
    }
}
