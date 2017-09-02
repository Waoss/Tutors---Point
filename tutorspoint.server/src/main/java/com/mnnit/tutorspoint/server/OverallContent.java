package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OverallContent {
    static OverallContent instance = new OverallContent();
    SimpleListProperty<Video> videoList = new SimpleListProperty<>(this, "videoList",
            FXCollections.observableArrayList());

    private OverallContent() {
    }

    public static OverallContent getInstance() {
        return instance;
    }

    public ObservableList<Video> getVideoList() {
        return videoList.get();
    }

    public SimpleListProperty<Video> videoListProperty() {
        return videoList;
    }

}
