package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;

public class DeleteLikeTask extends BooleanResponseTask {
    boolean isDeleted;

    public DeleteLikeTask(final int videoId) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") +
                "/deleteLike?videoId=" + videoId + "&username=" +
                System.getProperty("com.mnnit.tutorspoint.client.username")));
    }

    @Override
    protected Boolean call() throws Exception {
        return isDeleted = super.call();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(final boolean deleted) {
        isDeleted = deleted;
    }
}
