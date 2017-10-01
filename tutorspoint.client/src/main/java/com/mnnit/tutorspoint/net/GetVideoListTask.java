package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.Video;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Rohan on 01-10-2017.
 */
public class GetVideoListTask extends GenericResponsiveTask<Video[]> {
    public GetVideoListTask() throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getVideosList"), Video[].class);
    }
}
