package com.mnnit.tutorspoint;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class WatchVideosLayoutController implements Initializable {

    public ListView videoList;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> videoListContent = new SimpleListProperty<>(this, "videoListContent",
            FXCollections.observableArrayList());

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }

    public void lateInitialize() {
        videoListContent.get().addAll(VideoUtils.getAnchorPanesForVideos(getRetriever().retrieve()));
        videoList.setItems(videoListContent.get());
    }

    public VideoRetriever getRetriever() {
        return retriever.get();
    }

    public void setRetriever(final VideoRetriever retriever) {
        this.retriever.set(retriever);
    }

    public SimpleObjectProperty<VideoRetriever> retrieverProperty() {
        return retriever;
    }

    public ObservableList<AnchorPane> getVideoListContent() {
        return videoListContent.get();
    }

    public void setVideoListContent(final ObservableList<AnchorPane> videoListContent) {
        this.videoListContent.set(videoListContent);
    }

    public SimpleListProperty<AnchorPane> videoListContentProperty() {
        return videoListContent;
    }
}