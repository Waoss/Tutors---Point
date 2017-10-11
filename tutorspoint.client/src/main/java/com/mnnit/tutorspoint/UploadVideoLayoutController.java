package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.core.video.VideoCategory;
import com.mnnit.tutorspoint.net.FetchCategoryTask;
import com.mnnit.tutorspoint.net.InsertCategoryTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Logger;

@FXMLController
public class UploadVideoLayoutController {

    public static final Logger LOGGER = Logger.getLogger(UploadVideoLayoutController.class.getName());
    public Label messageLabel;
    public Button uploadFileButton;
    public TextField videoCategoryTextField;
    public TextField parentCategoryTextField;
    private FileChooser fileChooser = new FileChooser();

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(final Label messageLabel) {
        this.messageLabel = messageLabel;
    }

    public void uploadFileButtonOnAction(ActionEvent actionEvent) throws Exception {
        final File file = fileChooser.showOpenDialog(((Button) actionEvent.getSource()).getScene().getWindow());
        if (file == null) return;
        final Video video = new Video();
        video.setName(file.getName());
        video.setComments(Collections.emptyList());
        video.setLikes(Collections.emptyList());
        video.setTags(Collections.emptyList());
        video.setVideoId(-1);
        video.setDateTime(ZonedDateTime.now().toString());
        video.setCategory(videoCategoryTextField.getText());
        video.setUsername(System.getProperty("com.mnnit.tutorspoint.client.username"));
        video.setFormat(file.getName().substring(file.getName().lastIndexOf(".") + 1));

        //FIXME:Generic server URL
        /*final Task<Void> uploadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                LOGGER.info("Uploading video = " + Globals.GSON.toJson(video));

                return null;
            }
        };
        new Thread(uploadTask).start();
        while (uploadTask.isRunning()) {
            wait();
            LOGGER.info("The video is uploading.");
        }
        LOGGER.info("Video uploaded");*/


        Task<Void> uploadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                video.upload(System.getProperty("com.mnnit.tutorspoint.server.url") + "/upload", file);
                return null;
            }
        };
        Thread uploadVideoThread = new Thread(uploadTask);
        uploadVideoThread.setDaemon(true);
        uploadVideoThread.start();


        Platform.runLater(
                () -> new Alert(Alert.AlertType.INFORMATION, "The video was uploaded successfully").showAndWait());


        FetchCategoryTask fetchCategoryTask = new FetchCategoryTask(video.getCategory());
        Thread fetchCategoryThread = new Thread(fetchCategoryTask);
        fetchCategoryThread.setDaemon(true);
        fetchCategoryThread.start();

        Platform.runLater(() -> {
            VideoCategory[] videoCategories = fetchCategoryTask.getVideoCategories();
            List<VideoCategory> list = Arrays.asList(videoCategories);
            if (list.isEmpty()) {
                String parent = parentCategoryTextField.getText();
                try {
                    InsertCategoryTask insertCategoryTask = new InsertCategoryTask(video.getCategory(), parent);
                    Thread insertCategoryThread = new Thread(insertCategoryTask);
                    insertCategoryThread.setDaemon(true);
                    insertCategoryThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
