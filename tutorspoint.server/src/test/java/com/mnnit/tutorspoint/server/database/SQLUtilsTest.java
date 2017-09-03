package com.mnnit.tutorspoint.server.database;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Comment;
import com.mnnit.tutorspoint.core.video.Video;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SQLUtilsTest {

    @Test
    public void insertComment() throws Exception {
        Comment comment = new Comment("rohan23chhabra", "Well done uno!");
        comment.setVideoId(24);
        SQLUtils.insertComment(comment);
    }

    @Test
    public void getVideoById() throws Exception {
        Video video = SQLUtils.getVideoById(24);
        System.out.println(Globals.GSON.toJson(video));
    }

    @Test
    public void getVideoList() throws Exception {
        List<Video> videos = SQLUtils.getVideoList();
        final String s = Globals.GSON.toJson(videos);
        System.out.println(Arrays.toString(Globals.GSON.fromJson(s, Video[].class)));
    }

}