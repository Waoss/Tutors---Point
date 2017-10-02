package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ToWatchTabLayoutController implements Initializable {
    public ListView<AnchorPane> toWatchVideosView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            GenericResponsiveTask<Video[]> task = new GenericResponsiveTask<>(
                    new URL(System.getProperty("com.mnnit.tutorspoint.server.url") + "/getVideosByUserTodos?user=" +
                            System.getProperty("com.mnnit.tutorspoint.client.username")),
                    Video[].class
            );
            toWatchVideosView.setItems(
                    VideoUtils.getAnchorPanesForVideos(VideoRetriever.forGenericResponseTask(task).retrieve()));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
