package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface VideoRetriever {

    List<Video> retrieve() throws IOException, InterruptedException;
}
