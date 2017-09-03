package com.mnnit.tutorspoint.core.video;

import java.io.File;
import java.util.List;

public class Video {

    private int id = - 1;
    private String name;
    private String uploaderUsername;
    private String format;
    private List<Like> likes;
    private List<Comment> comments;
    private VideoCategory category;

    public void upload(String url, File content) throws Exception {
        VideoUploader videoUploader = new VideoUploader();
        videoUploader.setVideo(this);
        videoUploader.setFile(content);
        videoUploader.setUrl(url);
        videoUploader.sendRequest();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUploaderUsername() {
        return uploaderUsername;
    }

    public void setUploaderUsername(final String uploaderUsername) {
        this.uploaderUsername = uploaderUsername;
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

    public VideoCategory getCategory() {
        return category;
    }

    public void setCategory(final VideoCategory category) {
        this.category = category;
    }
}
