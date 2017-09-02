package com.mnnit.tutorspoint.database;

import com.mnnit.tutorspoint.core.*;
import com.mnnit.tutorspoint.core.video.*;
import javafx.collections.FXCollections;
import org.junit.Test;

public class SQLUtilsTest {
    @Test
    public void insertVideo() throws Exception {
        SQLUtils.insertVideo(
                new VideoBuilder()
                        .setVideoCategory(
                                new VideoCategoryBuilder()
                                        .setChildren(FXCollections.observableArrayList())
                                        .setName("DataStructures").createVideoCategory())
                        .setComments(FXCollections.observableArrayList())
                        .setFormat("mp4")
                        .setName("Hello")
                        .setUser(
                                new UserBuilder()
                                        .setUsername("roit")
                                        .setPassword("shorma")
                                        .setUserType(UserType.TEACHER)
                                        .createUser())
                        .setLikes(FXCollections.observableArrayList())
                        .createVideo());
    }

    @Test
    public void insertUser() throws Exception {
        User user = new UserBuilder()
                .setUsername("asdasd")
                .setPassword("asdasd")
                .setUserType(UserType.TEACHER)
                .createUser();
        SQLUtils.insertUser(user);
    }

    @Test
    public void getVideoById() throws Exception {
        Video video = SQLUtils.getVideoById(1);
    }

    @Test
    public void getUserByUsername() throws Exception {
        User user = SQLUtils.getUserByUsername("asdasd");
    }
}