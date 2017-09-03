package com.mnnit.tutorspoint;


import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class VideoListNode extends Region {
    private SimpleObjectProperty<Video> video = new SimpleObjectProperty<>(this, "video");
    private SimpleObjectProperty<Label> nameLabel = new SimpleObjectProperty<>(this, "nameLabel",
            new Label(video.get().getName()));
    private SimpleObjectProperty<Label> uploader = new SimpleObjectProperty<>(this, "uploader",
            new Label("Uploaded by: " + video.get().getUsername()));

    {
        getNameLabel().getStyleClass().add("video-list-node-name-label");
        video.addListener((observable, oldValue, newValue) -> {
            nameLabel.get().setText(newValue.getName());
            uploader.get().setText("Uploaded by: " + newValue.getUsername());
        });
    }

    public VideoListNode(final Video video) {
        setVideo(video);
    }

    public Video getVideo() {
        return video.get();
    }

    public void setVideo(final Video video) {
        this.video.set(video);
    }

    public SimpleObjectProperty<Video> videoProperty() {
        return video;
    }

    public Label getNameLabel() {
        return nameLabel.get();
    }

    public void setNameLabel(final Label nameLabel) {
        this.nameLabel.set(nameLabel);
    }

    public SimpleObjectProperty<Label> nameLabelProperty() {
        return nameLabel;
    }
}
