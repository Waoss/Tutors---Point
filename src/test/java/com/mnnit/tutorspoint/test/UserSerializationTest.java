package com.mnnit.tutorspoint.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserSerializationTest {

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(User.class, new UserTypeAdapter())
            .create();

    String serializedJson;

    @Test
    public void deserialize() throws Exception {
        serialize();
        User user = gson.fromJson(serializedJson, User.class);
        assertEquals(user.getUsername(), "roit");
        assertEquals(user.getPassword(), "shorma");
        assertEquals(user.getUserType(), UserType.STUDENT);
    }

    @Test
    public void serialize() throws Exception {
        User user = new UserBuilder()
                .setUsername("roit")
                .setPassword("shorma")
                .setUserType(UserType.valueOf("STUDENT"))
                .createUser();
        serializedJson = gson.toJson(user);
        System.out.println(serializedJson);
    }
}
