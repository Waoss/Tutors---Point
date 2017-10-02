package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.video.VideoCategory;

import java.io.IOException;
import java.net.URL;

public class GetCategoriesTask extends GenericResponsiveTask<VideoCategory[]> {
    public GetCategoriesTask(final String parent) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getSortedCategories")
                , VideoCategory[].class);
    }
}
