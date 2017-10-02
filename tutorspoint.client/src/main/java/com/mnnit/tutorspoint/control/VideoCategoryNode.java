package com.mnnit.tutorspoint.control;

import com.mnnit.tutorspoint.core.video.VideoCategory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class VideoCategoryNode extends AnchorPane {
    private SimpleObjectProperty<VideoCategory> videoCategory = new SimpleObjectProperty<>(this, "videoCategory");

    {
        videoCategory.addListener((observable, oldValue, newValue) -> this.getChildren()
                .setAll(new Label(newValue.getName() + " " + newValue.getRating())));
    }

    public VideoCategoryNode(final VideoCategory videoCategory) {
        this.videoCategory.set(videoCategory);
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
}
