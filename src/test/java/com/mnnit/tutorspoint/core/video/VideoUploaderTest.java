package com.mnnit.tutorspoint.core.video;

import javafx.collections.FXCollections;
import org.junit.Test;

import java.io.File;
import java.time.ZonedDateTime;

public class VideoUploaderTest {

    @Test
    public void sendRequest() throws Exception {
        Video video = new Video();
        video.setName("forbidden");
        video.setFormat("opus");
        video.setCategory(new VideoCategory("Music"));
        video.setComments(FXCollections.observableArrayList(new Comment("LOL!", "foo")));
        video.setLikes(FXCollections.observableArrayList());
        video.setUsername("martin girish");
        video.setDateTime(ZonedDateTime.now().toString());
        video.upload("http://localhost:9000/upload", new File("E:\\song videos\\forbidden.opus"));
    }
}
