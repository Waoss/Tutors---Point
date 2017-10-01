package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Tag;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class AddTagTask extends BooleanResponseTask {


    public AddTagTask(final Tag tag) throws IOException {
        super(new URL(
                System.getProperty("com.mnnit.tutorspoint.server.url") + "/insertTag?name=" +
                        URLEncoder.encode(tag.getName(), "utf-8") +
                        "&videoId=" + tag.getVideoId()));
    }
}
