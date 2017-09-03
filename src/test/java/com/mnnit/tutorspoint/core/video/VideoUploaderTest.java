package com.mnnit.tutorspoint.core.video;

import javafx.collections.FXCollections;
import org.junit.Test;

import java.io.File;
import java.time.ZonedDateTime;

public class VideoUploaderTest {

    @Test
    public void sendRequest() throws Exception {
        Video video = new Video();
        video.setName("ameer_pyaar");
        video.setFormat("mp4");
        video.setCategory(new VideoCategory("Music"));
        video.setComments(FXCollections.observableArrayList(new Comment("LOL!", "foo")));
        video.setLikes(FXCollections.observableArrayList());
        video.setUsername("UnoRespublicanas");
        video.setDateTime(ZonedDateTime.now().toString());
        video.upload("http://localhost:8000/upload", new File("E:\\Sungs\\ameer_pyaar.mp4"));
    }
}
