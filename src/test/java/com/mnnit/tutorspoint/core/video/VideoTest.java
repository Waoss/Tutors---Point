package com.mnnit.tutorspoint.core.video;

import com.mnnit.tutorspoint.core.User;
import org.junit.Test;

import java.io.File;

public class VideoTest {
    @Test
    public void upload() throws Exception {
        Video video = new VideoBuilder()
                .setUser(new User("foo", null, null))
                .setLikes(VideoTypeAdapterTest.getLikes())
                .setComments(VideoTypeAdapterTest.getComments())
                .setName("fade")
                .setFormat("mp4")
                .setVideoCategory(new VideoCategory("asd", null))
                .createVideo();
        Video.upload(video, new File("E:\\fade.mp4"));
    }

}