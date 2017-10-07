package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;

public class UpdateIsSentTask extends BooleanResponseTask {
    public UpdateIsSentTask(final String username) throws IOException {
        super(
                new URL(
                        System.getProperty("com.mnnit.tutorspoint.server.url") + "" +
                                "/updateIsSentForUser?user=" + username));
    }
}
