package com.mnnit.tutorspoint.control;

import com.mnnit.tutorspoint.core.video.Comment;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class CommentTableView extends TableView<Comment> {
    private final TableColumn<Comment, String> commenter = new TableColumn<>("Commenter");
    private final TableColumn<Comment, String> message = new TableColumn<>("Message");
    private final TableColumn<Comment, String> dateTime = new TableColumn<>("DateTime");

    {
        getColumns().addAll(commenter, message, dateTime);
        commenter.setCellValueFactory(param -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return param.getValue().getUsername();
            }
        });
        message.setCellValueFactory(param -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return param.getValue().getMessage();
            }
        });
        dateTime.setCellValueFactory(param -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return param.getValue().getDateTime();
            }
        });
    }

    public CommentTableView(final ObservableList<Comment> comments) {
        setItems(comments);
    }
}
