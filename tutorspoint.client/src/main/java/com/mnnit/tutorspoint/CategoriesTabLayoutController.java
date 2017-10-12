package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.control.VideoCategoryView;
import com.mnnit.tutorspoint.core.video.VideoCategory;
import com.mnnit.tutorspoint.net.GetCategoriesTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class CategoriesTabLayoutController implements Initializable {


    public VideoCategoryView categoriesListView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            categoriesListView.setItems(FXCollections.observableArrayList(getVideoCategoryList()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private ObservableList<VideoCategory> getVideoCategoryList() throws Throwable {
        final GetCategoriesTask getCategoriesTask = new GetCategoriesTask("Hyper Category");
        new Thread(getCategoriesTask).start();
        while (getCategoriesTask.isRunning()) {
            wait();
        }
        return FXCollections.observableArrayList(getCategoriesTask.get());
    }

    public void reloadOnAction(ActionEvent actionEvent) throws Throwable {
        categoriesListView.setItems(FXCollections.observableArrayList(getVideoCategoryList()));
    }
}
