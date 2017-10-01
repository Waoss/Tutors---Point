package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;

public class DeleteVideoTask extends BooleanResponseTask {
    public DeleteVideoTask(final int videoId) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/deleteVideo?videoId=" + videoId));
    }
}
