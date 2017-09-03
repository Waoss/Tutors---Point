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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public TreeMap<String, VideoCategory> getChildren() {
        return children;
    }

    public void setChildren(final TreeMap<String, VideoCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return name;
    }
}
