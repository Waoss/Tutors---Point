package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GetVideoListTask;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@FXMLController
public class WatchVideosLayoutController implements Initializable {

    public static final Logger LOGGER = Logger.getLogger(WatchVideosLayoutController.class.getName());
    public ListView videoList;
    public Button reloadButton;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> videoListContent = new SimpleListProperty<>(this, "videoListContent",
            FXCollections.observableArrayList());

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
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

    public void reloadOnAction(ActionEvent actionEvent) throws Throwable {
        setRetriever(() -> {
            GetVideoListTask getVideoListTask = null;
            try {
                getVideoListTask = new GetVideoListTask();
                LOGGER.info("Reload button called");
                new Thread(getVideoListTask).start();

                while (getVideoListTask.isRunning()) {
                    WatchVideosLayoutController.this.wait();
                    LOGGER.info("Waiting for server to reload page");
                }

                Video[] videos = getVideoListTask.get();
                return FXCollections.observableArrayList(videos);

            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        });
        lateInitialize();
    }

    public void lateInitialize() {
        try {
            videoListContent.get().setAll((VideoUtils.getAnchorPanesForVideos(getRetriever().retrieve()));
            videoList.setItems(videoListContent.get());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error occurred ", e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public VideoRetriever getRetriever() {
        return retriever.get();
    }

    public void setRetriever(final VideoRetriever retriever) {
        this.retriever.set(retriever);
    }
}