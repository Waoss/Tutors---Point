package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Like;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertLikeTask extends BooleanResponseTask {
    public InsertLikeTask(final Like like) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/insertLike?" +
                "username=" + like.getUsername() + "&dateTime=" + URLEncoder.encode(like.getDateTime(), "utf-8") +
                "&videoId=" +
                like.getVideoId()));
        httpURLConnection.setDoOutput(true);
        httpURLConnection.getOutputStream().write(Globals.GSON.toJson(like).getBytes("utf-8"));
    }
}