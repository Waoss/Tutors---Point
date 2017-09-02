package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoCategory {
    SimpleStringProperty name = new SimpleStringProperty(this, "name");
    ListProperty<VideoCategory> children = new SimpleListProperty<>(this, "children",
            FXCollections.observableArrayList());

    public VideoCategory(final String name,
                         final ObservableList<VideoCategory> children) {
        this.name.set(name);
        this.children.set(children);
    }

    public String getName() {
        return name.get();
    }

    public void setName(final String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public ObservableList<VideoCategory> getChildren() {
        return children.get();
    }

    public void setChildren(final ObservableList<VideoCategory> children) {
        this.children.set(children);
    }

    public ListProperty<VideoCategory> childrenProperty() {
        return children;
    }
}
