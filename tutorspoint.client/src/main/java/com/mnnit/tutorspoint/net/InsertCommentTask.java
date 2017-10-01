package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Comment;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertCommentTask extends BooleanResponseTask {
    public InsertCommentTask(final Comment comment) throws IOException {
        super(new URL(
                System.getProperty("com.mnnit.tutorspoint.server.url")
                        + "/insertComment?username=" + comment.getUsername()
                        + "&message=" + URLEncoder.encode(comment.getMessage(), "utf-8")
                        + "&dateTime=" + URLEncoder.encode(comment.getDateTime(), "utf-8")
                        + "&videoId=" + comment.getVideoId()));
    }
}
