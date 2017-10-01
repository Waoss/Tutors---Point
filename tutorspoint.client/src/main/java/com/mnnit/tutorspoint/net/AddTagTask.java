package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Tag;

import java.io.IOException;
import java.net.URL;

public class AddTagTask extends BooleanResponseTask {


    public AddTagTask(final Tag tag) throws IOException {
        super(new URL(
                System.getProperty("com.mnnit.tutorspoint.server.url") + "/insertTag?name=" + tag.getName() +
                        "&videoId=" + tag.getVideoId()));
    }
}
