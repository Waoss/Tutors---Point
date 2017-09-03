package com.mnnit.tutorspoint.core.video;

import java.util.TreeMap;

public class VideoCategory {

    String name;
    TreeMap<String, VideoCategory> children = new TreeMap<>();

    public VideoCategory() {
    }

    public VideoCategory(final String name) {
        this.name = name;
    }
}
