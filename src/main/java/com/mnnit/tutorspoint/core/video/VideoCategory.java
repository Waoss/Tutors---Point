package com.mnnit.tutorspoint.core.video;

public class VideoCategory {

    String name;

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

    @Override
    public String toString() {
        return name;
    }
}
