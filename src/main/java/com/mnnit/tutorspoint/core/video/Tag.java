package com.mnnit.tutorspoint.core.video;

public class Tag {
    private String name;
    private int videoId;

    public Tag(final String name, final int videoId) {
        this.name = name;
        this.videoId = videoId;
    }

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(final int videoId) {
        this.videoId = videoId;
    }
}
