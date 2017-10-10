package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;

public class DeleteCommentTask extends BooleanResponseTask {
    boolean isCommentDeleted;

    public DeleteCommentTask(final int videoId, final String username) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/deleteComment" +
                "?videoId=" + videoId + "&username=" + username));
    }

    @Override
    protected Boolean call() throws Exception {
        return isCommentDeleted = super.call();
    }

    public boolean isCommentDeleted() {
        return isCommentDeleted;
    }

    public void setCommentDeleted(final boolean commentDeleted) {
        isCommentDeleted = commentDeleted;
    }
}
