package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class VideoUtils {
    public static List<AnchorPane> getAnchorPanesForVideos(
            List<Video> videos) {
        final Vector<AnchorPane> result = new Vector<>();
        videos.forEach(video -> {
            try {
                final FXMLLoader fxmlLoader = new FXMLLoader(
                        WatchVideosLayoutController.class.getResource("/layout/VideoLayout.fxml"));
                final AnchorPane anchorPane = fxmlLoader.load();
                result.add(anchorPane);
                final VideoLayoutController controller = fxmlLoader.getController();
                //Fixme:Generic Server URL
                controller.setUrl(System.getProperty("com.mnnit.tutorspoint.server.url") + "/");
                controller.setVideo(video);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }
}
