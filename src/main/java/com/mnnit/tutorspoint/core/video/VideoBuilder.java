package com.mnnit.tutorspoint.core.video;

import com.mnnit.tutorspoint.core.User;
import javafx.collections.ObservableList;

public class VideoBuilder {
    private ObservableList comments;
    private ObservableList<Like> likes;
    private String name;
    private String format;
    private VideoCategory videoCategory;
    private User user;

    public VideoBuilder setLikes(final ObservableList<Like> likes) {
        this.likes = likes;
        return this;
    }

    public VideoBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public VideoBuilder setFormat(final String format) {
        this.format = format;
        return this;
    }

    public VideoBuilder setVideoCategory(final VideoCategory videoCategory) {
        this.videoCategory = videoCategory;
        return this;
    }

    public VideoBuilder setComments(final ObservableList comments) {
        this.comments = comments;
        return this;
    }

    public VideoBuilder setUser(final User user) {
        this.user = user;
        return this;
    }

    public Video createVideo() {
        Video result = new Video();
        result.setName(name);
        result.setFormat(format);
        result.setComments(comments);
        result.setLikes(likes);
        result.setVideoCategory(videoCategory);
        result.setUser(user);
        return result;
    }
}