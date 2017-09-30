package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Video;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.logging.Logger;

@FXMLController
public class UploadVideoLayoutController {

    public static final Logger LOGGER = Logger.getLogger(UploadVideoLayoutController.class.getName());
    public Label messageLabel;
    public Button uploadFileButton;
    public TextField videoCategoryTextField;
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
        final Task<Void> uploadTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                LOGGER.info("Uploading video = " + Globals.GSON.toJson(video));
                video.upload(System.getProperty("com.mnnit.tutorspoint.server.url") + "/upload", file);
                return null;
            }
        };
        new Thread(uploadTask).start();
        while (uploadTask.isRunning()) {
            LOGGER.info("The video is uploading.");
        }
        LOGGER.info("Video uploaded");
        new Alert(Alert.AlertType.INFORMATION, "The video was uploaded successfully").showAndWait();
    }
}
