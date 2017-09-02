package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

public class Video {

    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty name = new SimpleStringProperty();
    SimpleStringProperty uploaderUsername = new SimpleStringProperty();
    SimpleStringProperty format = new SimpleStringProperty();
    SimpleListProperty<Like> likes = new SimpleListProperty<>();
    SimpleListProperty<Comment> comments = new SimpleListProperty<>();
    SimpleObjectProperty<VideoCategory> category = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(final int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public String getUploaderUsername() {
        return uploaderUsername.get();
    }

    public SimpleStringProperty uploaderUsernameProperty() {
        return uploaderUsername;
    }

    public void setUploaderUsername(final String uploaderUsername) {
        this.uploaderUsername.set(uploaderUsername);
    }

    public String getFormat() {
        return format.get();
    }

    public SimpleStringProperty formatProperty() {
        return format;
    }

    public void setFormat(final String format) {
        this.format.set(format);
    }

    public ObservableList<Like> getLikes() {
        return likes.get();
    }

    public SimpleListProperty<Like> likesProperty() {
        return likes;
    }

    public void setLikes(final ObservableList<Like> likes) {
        this.likes.set(likes);
    }

    public ObservableList<Comment> getComments() {
        return comments.get();
    }

    public SimpleListProperty<Comment> commentsProperty() {
        return comments;
    }

    public void setComments(final ObservableList<Comment> comments) {
        this.comments.set(comments);
    }

    public VideoCategory getCategory() {
        return category.get();
    }

    public SimpleObjectProperty<VideoCategory> categoryProperty() {
        return category;
    }

    public void setCategory(final VideoCategory category) {
        this.category.set(category);
    }
}
