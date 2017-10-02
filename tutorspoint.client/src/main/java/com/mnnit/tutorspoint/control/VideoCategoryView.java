package com.mnnit.tutorspoint.control;

import com.mnnit.tutorspoint.core.video.VideoCategory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class VideoCategoryView extends TableView<VideoCategory> {
    private final TableColumn<VideoCategory, String> nameColumn = new TableColumn<>("Name");
    private final TableColumn<VideoCategory, String> parentNameColumn = new TableColumn<>("SubCategory Of");
    private final TableColumn<VideoCategory, String> ratingColumn = new TableColumn<>("Rating");

    {
        getColumns().addAll(nameColumn, parentNameColumn, ratingColumn);
        nameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        parentNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getParentName()));
        ratingColumn
                .setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getRating())));
    }

    public VideoCategoryView(final ObservableList<VideoCategory> videoCategories) {
        setItems(videoCategories);
    }

    public VideoCategoryView() {
    }

    public TableColumn<VideoCategory, String> getNameColumn() {
        return nameColumn;
    }

    public TableColumn<VideoCategory, String> getParentNameColumn() {
        return parentNameColumn;
    }

    public TableColumn<VideoCategory, String> getRatingColumn() {
        return ratingColumn;
    }
}
