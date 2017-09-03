package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1000000000)// 100 mb
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        Part metadataPart = request.getPart("metadata");
        Video video = Globals.GSON.fromJson(new InputStreamReader(metadataPart.getInputStream()), Video.class);
    }

}
