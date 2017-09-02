package com.mnnit.tutorspoint.core.video;

import javafx.collections.ObservableList;

public class VideoCategoryBuilder {
    private String name;
    private ObservableList<VideoCategory> children;

    public VideoCategoryBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public VideoCategoryBuilder setChildren(final ObservableList<VideoCategory> children) {
        this.children = children;
        return this;
    }

    public VideoCategory createVideoCategory() {
        return new VideoCategory(name, children);
    }
}