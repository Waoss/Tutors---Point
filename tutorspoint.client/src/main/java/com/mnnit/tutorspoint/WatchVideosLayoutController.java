package com.mnnit.tutorspoint;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WatchVideosLayoutController implements Initializable {
    SimpleListProperty<VideoListNode> videoListNodes = new SimpleListProperty<>(this, "videoListNodes",
            FXCollections.observableArrayList());

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }
}
