package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.control.VideoCategoryNode;
import com.mnnit.tutorspoint.core.video.VideoCategory;
import com.mnnit.tutorspoint.net.GetCategoriesTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class CategoriesTabLayoutController implements Initializable {

    public ListView<VideoCategoryNode> categoriesListView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            categoriesListView.setItems(FXCollections.observableArrayList(getVideoCategoryNodeList()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private ObservableList<VideoCategoryNode> getVideoCategoryNodeList() throws Throwable {
        final GetCategoriesTask getCategoriesTask = new GetCategoriesTask("Hyper Category");
        new Thread(getCategoriesTask).start();
        while (getCategoriesTask.isRunning()) {
            wait();
        }
        final ObservableList<VideoCategoryNode> result = FXCollections.observableArrayList();
        VideoCategory[] videoCategoryArray = getCategoriesTask.get();
        for (int i = 0; i < videoCategoryArray.length; i++) {
            VideoCategory videoCategory = videoCategoryArray[i];
            result.add(new VideoCategoryNode(videoCategory));
        }
        return result;
    }
}
