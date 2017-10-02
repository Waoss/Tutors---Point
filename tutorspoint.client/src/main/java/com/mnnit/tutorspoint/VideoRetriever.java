package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface VideoRetriever {

    static VideoRetriever forGenericResponseTask(GenericResponsiveTask<Video[]> task) {
        return new VideoRetrieverForGenericResponsiveTask(task);
    }

    List<Video> retrieve() throws IOException, InterruptedException;
}
