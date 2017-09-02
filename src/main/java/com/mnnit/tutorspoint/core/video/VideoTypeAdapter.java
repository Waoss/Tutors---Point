package com.mnnit.tutorspoint.core.video;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mnnit.tutorspoint.core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.ZonedDateTime;

public class VideoTypeAdapter extends TypeAdapter<Video> {
    @Override
    public void write(final JsonWriter jsonWriter, final Video video) throws IOException {
        jsonWriter
                .beginObject()
                .name("name").value(video.getName())
                .name("format").value(video.getFormat())
                .name("user").value(video.getUser().getPassword())
                .name("likes").beginArray();
        video.getLikes().forEach(like -> {
            try {
                jsonWriter.beginObject()
                        .name("user").value(like.getUser().getUsername())
                        .name("dateTime").value(like.getDateTime().toString());
                jsonWriter.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonWriter.endArray();
        jsonWriter.name("category").value(video.getVideoCategory().getName())
                .name("comments").beginArray();
        video.getComments().forEach(comment -> {
            try {
                jsonWriter.beginObject();
                jsonWriter.name("commentor").value(comment.getUser().getUsername())
                        .name("message").value(comment.getMessage())
                        .name("dateTime").value(comment.getDateTime().toString());
                jsonWriter.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    @Override
    public Video read(final JsonReader jsonReader) throws IOException {
        Video video = new Video();
        jsonReader.beginObject();
        jsonReader.nextName();
        String name = jsonReader.nextString();
        jsonReader.nextName();
        String format = jsonReader.nextString();
        jsonReader.nextName();
        String user = jsonReader.nextString();
        jsonReader.nextName();
        ObservableList<Like> likes = FXCollections.observableArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String likeUser = jsonReader.nextString();
            jsonReader.nextName();
            ZonedDateTime likeDateTime = ZonedDateTime.parse(jsonReader.nextString());
            jsonReader.endObject();
            Like like = new Like();
            like.setUser(new User(likeUser, null, null));
            like.setDateTime(likeDateTime);
            likes.add(like);
        }
        jsonReader.endArray();
        jsonReader.nextName();
        String category = jsonReader.nextString();
        jsonReader.nextName();
        jsonReader.beginArray();
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String commentUser = jsonReader.nextString();
            jsonReader.nextName();
            String message = jsonReader.nextString();
            jsonReader.nextName();
            ZonedDateTime commentZonedDateTime = ZonedDateTime.parse(jsonReader.nextString());
            jsonReader.endObject();
            Comment comment = new Comment();
            comment.setUser(new User(commentUser, null, null));
            comment.setDateTime(commentZonedDateTime);
            comments.add(comment);
        }
        jsonReader.endArray();
        jsonReader.endObject();
        Video result = new Video();
        result.setName(name);
        result.setUser(new User(user, null, null));
        result.setVideoCategory(new VideoCategory(category, null));
        result.setLikes(likes);
        result.setComments(comments);
        return result;
    }
}
