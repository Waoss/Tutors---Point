package com.mnnit.tutorspoint.net;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class UpdateRatingTask extends BooleanResponseTask {
    public UpdateRatingTask(final int rating, final String category) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                + "/updateRating?rating=" + rating + "&category=" + URLEncoder.encode(category, "utf-8")));
    }

    @Override
    protected Boolean call() throws Exception {
        return super.call();
    }
}
