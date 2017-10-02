package com.mnnit.tutorspoint;

import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.net.GenericResponsiveTask;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Internal
class VideoRetrieverForGenericResponsiveTask implements VideoRetriever {

    private final GenericResponsiveTask<Video[]> task;

    public VideoRetrieverForGenericResponsiveTask(GenericResponsiveTask<Video[]> task) {
        this.task = task;
    }

    @Override
    public List<Video> retrieve() throws IOException, InterruptedException {

        try {
            new Thread(task).start();
            while (task.isRunning()) {
                wait();
            }
            return FXCollections.observableArrayList(task.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return FXCollections.emptyObservableList();
    }
}
