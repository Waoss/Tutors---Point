package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WatchVideosLayoutController implements Initializable {

    public ListView videoList;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> videoListContent = new SimpleListProperty<>(this, "videoListContent",
            FXCollections.observableArrayList());

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
    }

    public void lateInitialize() {
        videoListContent.get().addAll(getAnchorPanesForVideos(getRetriever().retrieve()));
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

    private List<AnchorPane> getAnchorPanesForVideos(List<Video> videos) {
        final Vector<AnchorPane> result = new Vector<>();
        videos.forEach(video -> {
            try {
                final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/VideoLayout.fxml"));
                final AnchorPane anchorPane = fxmlLoader.load();
                result.add(anchorPane);
                final VideoLayoutController controller = fxmlLoader.getController();
                //Fixme:Generic Server URL
                controller.setUrl("http://localhost:8000/");
                controller.setVideo(video);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
