package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.List;

public class VideoUtils {
    public static ObservableList<AnchorPane> getAnchorPanesForVideos(
            List<Video> videos) {
        final ObservableList<AnchorPane> result = FXCollections.observableArrayList();
        videos.forEach(video -> {
            try {
                if (video != null) {
                    final FXMLLoader fxmlLoader = new FXMLLoader(
                            WatchVideosLayoutController.class.getResource("/layout/VideoLayout.fxml"));
                    final AnchorPane anchorPane = fxmlLoader.load();

                    final VideoLayoutController controller = fxmlLoader.getController();
                    //Fixme:Generic Server URL
                    controller.setUrl(System.getProperty("com.mnnit.tutorspoint.server.url") + "/");
                    controller.setVideo(video);
                    if (video.getUsername() != null) {
                        result.add(anchorPane);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
