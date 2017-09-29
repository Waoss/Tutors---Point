package com.mnnit.tutorspoint.server;

import com.mnnit.tutorspoint.core.Globals;
import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.server.database.SQLUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1000000000)// 100 mb
public class UploadServlet extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(UploadServlet.class.getName());

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        Part metadataPart = request.getPart("metadata");
        Video video = Globals.GSON.fromJson(new InputStreamReader(metadataPart.getInputStream()), Video.class);
        try {
            SQLUtils.insertVideo(video);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.info("Uploaded video with following metadata; as received from client \n" + Globals.GSON.toJson(video));
        Part binaryFilePart = request.getPart("binaryFile");
        LOGGER.info("Got the actual binary part");
        final String realPath = getServletContext().getRealPath("");
        File file = new File(realPath, video.getVideoId() + ".vid");
        file.createNewFile();
        LOGGER.info("The new video is at " + file.toURI().toString());
        IOUtils.copy(binaryFilePart.getInputStream(), new FileOutputStream(file));
        response.getWriter().print("0");
    }
}
