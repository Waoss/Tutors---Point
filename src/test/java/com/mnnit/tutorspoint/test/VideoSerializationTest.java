package com.mnnit.tutorspoint.test;

import com.google.gson.Gson;
import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Comment;
import com.mnnit.tutorspoint.core.video.Video;
import javafx.collections.FXCollections;
import org.junit.Test;

public class VideoSerializationTest {

    Gson gson = Globals.GSON;
    String serializedJson;

    @Test
    public void serialize() throws Exception {
        Video video = new Video();
        video.setName("foo");
        video.setFormat("mp4");
        video.setCategory("Data-Structures");
        video.setComments(FXCollections.observableArrayList(new Comment("Hello", "asd")));
        video.setLikes(FXCollections.observableArrayList());
        video.setVideoId(- 1);
        serializedJson = gson.toJson(video);
        System.out.println(serializedJson);
    }

    @Test
    public void deserialize() throws Exception {
        serialize();
        Video video = gson.fromJson(serializedJson, Video.class);
        System.out.println(video);
        System.out.println(video.getName());
    }
}
