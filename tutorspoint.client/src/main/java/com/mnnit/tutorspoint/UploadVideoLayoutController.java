package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Collections;

public class UploadVideoLayoutController {

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
        final Video video = new Video();
        video.setName(file.getName());
        video.setComments(Collections.emptyList());
        video.setLikes(Collections.emptyList());
        video.setVideoId(- 1);
        video.setDateTime(ZonedDateTime.now().toString());
        video.setCategory(videoCategoryTextField.getText());
        //FIXME:Generic server URL
        video.upload(System.getProperty("com.mnnit.tutorspoint.server.url") + "/upload", file);
    }
}
