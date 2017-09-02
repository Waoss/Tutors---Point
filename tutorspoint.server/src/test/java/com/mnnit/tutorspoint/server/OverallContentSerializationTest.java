package com.mnnit.tutorspoint.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.video.VideoBuilder;
import org.junit.Test;

public class OverallContentSerializationTest {
    @Test
    public void serialize() throws Exception {
        OverallContent overallContent = OverallContent.getInstance();
        overallContent.getVideoList().add(new VideoBuilder().createVideo());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(overallContent));
    }
}
