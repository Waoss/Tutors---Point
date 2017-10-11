package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.VideoCategory;

import java.io.IOException;
import java.net.URL;

public class FetchCategoryTask extends GenericResponsiveTask<VideoCategory[]> {
    VideoCategory[] videoCategories;

    public FetchCategoryTask(final String name) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                        + "/getCategoriesByName?name=" + name)
                , VideoCategory[].class);
    }

    @Override
    protected VideoCategory[] call() throws Exception {
        return videoCategories = super.call();
    }

    public VideoCategory[] getVideoCategories() {
        return videoCategories;
    }

    public void setVideoCategories(final VideoCategory[] videoCategories) {
        this.videoCategories = videoCategories;
    }
}
