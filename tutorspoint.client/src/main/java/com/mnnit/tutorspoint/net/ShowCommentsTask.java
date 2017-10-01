package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Comment;

import java.io.IOException;
import java.net.URL;

public class ShowCommentsTask extends GenericResponsiveTask<Comment[]> {
    public ShowCommentsTask(final int videoId) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") +
                "/getCommentsByVideoId?videoId=" + videoId), Comment[].class);
    }
}
