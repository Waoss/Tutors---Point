package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Tag;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Rohan on 02-10-2017.
 */
public class ShowTagsTask extends GenericResponsiveTask<Tag[]> {
    public ShowTagsTask(final int videoId) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") +
                "/getTagsByVideoId?videoId=" + videoId), Tag[].class);
    }
}
