package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.control.VideoCategoryView;
import com.mnnit.tutorspoint.core.InProgress;
import com.mnnit.tutorspoint.core.video.VideoCategory;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class InProgressTabLayoutController implements Initializable {
    public VideoCategoryView inProgressView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        inProgressView.setItems(getInProgressVideos(System.getProperty("com.mnnit.tutorspoint.client.username")));
    }

    private ObservableList<VideoCategory> getInProgressVideos(final String username) {

        try {
            GenericResponsiveTask<InProgress[]> getInProgressVideosTask = new GenericResponsiveTask<>(
                    new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                            + "/getInProgressByUser?user=" + username), InProgress[].class
            );

            new Thread(getInProgressVideosTask).start();
            while (getInProgressVideosTask.isRunning()) {
                wait();
            }

            InProgress[] inProgressVideos = getInProgressVideosTask.get();
            final ObservableList<VideoCategory> result = FXCollections.observableArrayList();
            for (int i = 0; i < inProgressVideos.length; i++) {
                InProgress inProgressVideo = inProgressVideos[i];
                result.add(inProgressVideo.getCategory());
            }
            return result;
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }
}
