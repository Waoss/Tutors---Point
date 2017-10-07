package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.logging.Logger;

@FXMLController
public class SearchVideoTabLayoutController {
    public static final Logger LOGGER = Logger.getLogger(SearchVideoTabLayoutController.class.getName());
    public ListView<AnchorPane> videoList;
    public Button searchByTag;
    public TextField tagName;
    public TextField uploaderName;
    public Button searchByUploader;
    public TextField categoryName;
    public Button searchByCategory;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> anchorPanes = new SimpleListProperty<>(this, "anchorPanes");

    public void searchByTagOnAction(ActionEvent actionEvent) throws Throwable {
        if (!isValid(SearchType.SEARCH_BY_TAG)) {
            return;
        }
        String tagName = this.tagName.getText();
        final GenericResponsiveTask<Video[]> getVideosByTagTask = new GenericResponsiveTask<>(
                new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getVideosByTag?name=" + tagName),
                Video[].class);
        new Thread(getVideosByTagTask).start();

        while (getVideosByTagTask.isRunning()) {
            LOGGER.info("Waiting for server to respond");
            wait();
        }

        Video[] videos = getVideosByTagTask.get();
        videoList
                .setItems(FXCollections.observableArrayList(VideoUtils.getAnchorPanesForVideos(Arrays.asList(videos))));
    }

    private boolean isValid(SearchType searchType) {
        if (searchType == SearchType.SEARCH_BY_TAG) {
            if (tagName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter a valid tag!");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            if (!uploaderName.getText().isEmpty() || !categoryName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Only one field required");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            return true;
        } else if (searchType == SearchType.SEARCH_BY_CATEGORY) {
            if (categoryName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter a valid category!");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            if (!tagName.getText().isEmpty() || !uploaderName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Only one field required!");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            return true;
        } else {
            if (!tagName.getText().isEmpty() || !categoryName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Only one field required!");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            if (uploaderName.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please enter a valid uploader!");
                alert.showAndWait().filter(response -> response == ButtonType.OK);
                return false;
            }
            return true;
        }
    }

    public void searchByUploaderOnAction(ActionEvent actionEvent) throws Throwable {
        if (!isValid(SearchType.SEARCH_BY_UPLOADER)) {
            return;
        }
        String uploaderName = this.uploaderName.getText();

        final GenericResponsiveTask<Video[]> getVideosByUploader = new GenericResponsiveTask<>(
                new URL(System.getProperty("com.mnnit.tutorspoint.server.url") +
                        "/getVideosByUploader?uploader=" +
                        URLEncoder.encode(uploaderName, "utf-8")), Video[].class
        );

        new Thread(getVideosByUploader).start();

        while (getVideosByUploader.isRunning()) {
            wait();
            LOGGER.info("Waiting for server to respond");
        }

        Video[] videos = getVideosByUploader.get();
        videoList
                .setItems(FXCollections.observableArrayList(VideoUtils.getAnchorPanesForVideos(Arrays.asList(videos))));
    }

    public void searchByCategoryOnAction(ActionEvent actionEvent) throws Throwable {
        if (!isValid(SearchType.SEARCH_BY_CATEGORY)) {
            return;
        }

        String categoryName = this.categoryName.getText();
        final GenericResponsiveTask<Video[]> getVideosByCategory = new GenericResponsiveTask<>(
                new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getVideosByCategory?category=" +
                        categoryName), Video[].class);

        new Thread(getVideosByCategory).start();
        while (getVideosByCategory.isRunning()) {
            wait();
            LOGGER.info("Waiting for server to respond");
        }

        Video[] videos = getVideosByCategory.get();
        videoList
                .setItems(FXCollections.observableArrayList(VideoUtils.getAnchorPanesForVideos(Arrays.asList(videos))));
    }

    private enum SearchType {
        SEARCH_BY_TAG, SEARCH_BY_UPLOADER, SEARCH_BY_CATEGORY
    }
}
