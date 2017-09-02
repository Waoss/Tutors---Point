package com.mnnit.tutorspoint.core.video;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.User;
import com.mnnit.tutorspoint.core.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;

import java.time.ZonedDateTime;

public class VideoTypeAdapterTest {
    Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Video.class, new VideoTypeAdapter()).create();
    String json;

    @Test
    public void read() throws Exception {
        write();
        Video video = gson.fromJson(json, Video.class);
    }

    @Test
    public void write() throws Exception {
        Video video = new VideoBuilder()
                .setName("fade")
                .setFormat("mp4")
                .setUser(new User("foo", "bar", UserType.STUDENT))
                .setVideoCategory(new VideoCategoryBuilder().setName("Crypto").createVideoCategory())
                .setLikes(getLikes())
                .setComments(getComments())
                .createVideo();
        video.setId(-1);
        json = gson.toJson(video);
    }

    public static ObservableList<Like> getLikes() {
        ObservableList<Like> likes = FXCollections.observableArrayList();
        Like like = new Like();
        like.setUser(new User("a", null, null));
        like.setDateTime(ZonedDateTime.now());
        likes.add(like);
        return likes;

    }

    public static ObservableList<Comment> getComments() {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        Comment comment = new Comment();
        comment.setMessage("Chutiya ho tum");
        comment.setUser(new User("b", null, null));
        comment.setDateTime(ZonedDateTime.now());
        comments.add(comment);
        return comments;
    }
}