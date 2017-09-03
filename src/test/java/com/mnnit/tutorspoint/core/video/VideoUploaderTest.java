package com.mnnit.tutorspoint.core.video;

import javafx.collections.FXCollections;
import org.junit.Test;

import java.io.File;

public class VideoUploaderTest {

    @Test
    public void sendRequest() throws Exception {
        Video video = new Video();
        video.setName("fade");
        video.setFormat("mp4");
        video.setCategory(new VideoCategory("Data-Structures"));
        video.setComments(FXCollections.observableArrayList(new Comment("LOL!", "foo")));
        video.setLikes(FXCollections.observableArrayList());
        video.setUploaderUsername("waqar");
        video.upload("http://localhost:8000/upload", new File("E:\\cloc-1.64.exe"));
    }
}
