package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.Serializable;

public class Video extends UserActivity implements Serializable {
    public static final long serialVersionUID = 324;
    SimpleListProperty<Comment> comments = new SimpleListProperty<Comment>(this, "comments",
            FXCollections.observableArrayList());
    SimpleListProperty<Like> likes = new SimpleListProperty<>(this, "likes", FXCollections.observableArrayList());
    SimpleStringProperty name = new SimpleStringProperty(this, "name");
    StringProperty format = new SimpleStringProperty(this, "format");
    SimpleObjectProperty<VideoCategory> videoCategory = new SimpleObjectProperty<>(this, "videoCategory");
    SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");

    public Video() {
    }

    public static final void upload(Video video, File file) throws Exception {
        VideoUploader videoUploader = new VideoUploader();
        videoUploader.setVideo(video);
        videoUploader.setFileToUpload(file);
        videoUploader.upload();
    }

    public ObservableList<Comment> getComments() {
        return comments.get();
    }

    public void setComments(final ObservableList<Comment> comments) {
        this.comments.set(comments);
    }

    public SimpleListProperty<Comment> commentsProperty() {
        return comments;
    }

    public ObservableList<Like> getLikes() {
        return likes.get();
    }

    public void setLikes(final ObservableList<Like> likes) {
        this.likes.set(likes);
    }

    public SimpleListProperty<Like> likesProperty() {
        return likes;
    }

    public String getName() {
        return name.get();
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getFormat() {
        return format.get();
    }

    public void setFormat(final String format) {
        this.format.set(format);
    }

    public StringProperty formatProperty() {
        return format;
    }

    public VideoCategory getVideoCategory() {
        return videoCategory.get();
    }

    public void setVideoCategory(final VideoCategory videoCategory) {
        this.videoCategory.set(videoCategory);
    }

    public SimpleObjectProperty<VideoCategory> videoCategoryProperty() {
        return videoCategory;
    }

    public int getId() {
        return id.get();
    }

    public void setId(final int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
