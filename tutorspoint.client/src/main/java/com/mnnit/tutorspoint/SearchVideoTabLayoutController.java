package com.mnnit.tutorspoint;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class SearchVideoTabLayoutController {
    public ListView videoList;
    public Button search;
    public TextField videoName;
    public TextField uploaderName;
    private SimpleObjectProperty<VideoRetriever> retriever = new SimpleObjectProperty<>(this, "retriever");
    private SimpleListProperty<AnchorPane> anchorPanes = new SimpleListProperty<>(this, "anchorPanes");

    public void onSearchClicked(ActionEvent actionEvent) {
        String videoName = this.videoName.getText();
        String uploaderName = this.uploaderName.getText();
        if (!videoName.equals("") && !uploaderName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please search by only one field at a time!");
            alert.showAndWait().filter(response -> response == ButtonType.OK);
        }
    }
}
