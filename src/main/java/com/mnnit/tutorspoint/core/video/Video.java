package com.mnnit.tutorspoint.core.video;

import java.io.File;
import java.util.List;

public class Video extends UserActivity {

    private String name;
    private String format;
    private List<Like> likes;
    private List<Comment> comments;
    private String category;

    public void upload(String url, File content) throws Exception {
        VideoUploader videoUploader = new VideoUploader();
        videoUploader.setVideo(this);
        videoUploader.setFile(content);
        videoUploader.setUrl(url);
        videoUploader.sendRequest();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(final List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }
}
