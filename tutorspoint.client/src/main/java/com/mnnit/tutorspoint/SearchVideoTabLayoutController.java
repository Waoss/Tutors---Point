package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;

@FXMLController
public class SearchVideoTabLayoutController {
    public ListView videoList;
    public Button search;
    public TextField videoName;
    public TextField uploaderName;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> anchorPanes = new SimpleListProperty<>(this, "anchorPanes");

    public void onSearchClicked(ActionEvent actionEvent) throws IOException {
        String tagName = this.videoName.getText();
        String uploaderName = this.uploaderName.getText();
        if (!tagName.equals("") && !uploaderName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please search by only one field at a time!");
            alert.showAndWait().filter(response -> response == ButtonType.OK);
            return;
        }
        final GenericResponsiveTask<Video[]> getVideosByTagTask = new GenericResponsiveTask<>(
                new URL(System.getProperty("com.mnnit.tutorspoint.server") + "/getVideosByTag?name=" + tagName),
                Video[].class);
        final GenericResponsiveTask<Video[]> getVideosByUploader = new GenericResponsiveTask<>(
                new URL(System.getProperty("com.mnnit.tutorspoint.server") + "/getVideosBy"), Video[].class
        );
    }
}
