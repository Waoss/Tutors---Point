package com.mnnit.tutorspoint.core.video;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.media.*;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.File;
import java.time.ZonedDateTime;

public class VideoUploaderTest extends Application {

    Video video = new Video();

    @Test
    public void sendRequest() throws Exception {
        video.setName("ameer_pyaar");
        video.setFormat("mp4");
        video.setCategory("Music");
        video.setComments(FXCollections.observableArrayList(new Comment("rajni", "lol")));
        video.setLikes(FXCollections.observableArrayList());
        video.setUsername("AlanWalker");
        video.setDateTime(ZonedDateTime.now().toString());
        video.upload("http://localhost:8000/upload", new File("E:\\Sungs\\ameer_pyaar.mp4"));
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        Media media = new Media("http://localhost:8000/25.vid");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        Group group = new Group(mediaView);
        primaryStage.setScene(new Scene(group));
        primaryStage.setTitle("Test");
        primaryStage.show();
        mediaPlayer.play();
    }

    @Test
    public void runMedia() throws Exception {
        launch();
    }
}
