package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;

public class UpdateIsSentTask extends BooleanResponseTask {
    boolean isSent;

    public UpdateIsSentTask(final String username) throws IOException {
        super(
                new URL(
                        System.getProperty("com.mnnit.tutorspoint.server.url") + "" +
                                "/updateIsSentForUser?user=" + username));
    }

    @Override
    protected Boolean call() throws Exception {
        return isSent = super.call();
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(final boolean sent) {
        isSent = sent;
    }
}
