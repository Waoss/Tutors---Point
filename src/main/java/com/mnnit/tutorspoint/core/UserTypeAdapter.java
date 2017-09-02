package com.mnnit.tutorspoint.core;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class UserTypeAdapter extends TypeAdapter<User> {

    @Override
    public void write(final JsonWriter jsonWriter, final User user) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("username").value(user.getUsername());
        jsonWriter.name("password").value(user.getPassword());
        jsonWriter.name("usertype").value(user.getUserType().toString());
        jsonWriter.endObject();
    }

    @Override
    public User read(final JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        String username = jsonReader.nextString();
        jsonReader.nextName();
        String password = jsonReader.nextString();
        jsonReader.nextName();
        String usertype = jsonReader.nextString();
        jsonReader.endObject();
        return new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setUserType(UserType.valueOf(usertype))
                .createUser();
    }
}
