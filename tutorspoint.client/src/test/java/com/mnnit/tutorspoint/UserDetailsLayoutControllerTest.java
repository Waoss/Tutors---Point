package com.mnnit.tutorspoint;

import org.junit.Test;

public class UserDetailsLayoutControllerTest {

    @Test
    public void getVideos() throws Exception {
        System.out.println(new UserDetailsLayoutController().getVideos("http://localhost:8000/getVideosList"));
    }
}