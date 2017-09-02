package com.mnnit.tutorspoint.server;

import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.database.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

@WebServlet("/uploadvideo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 50,          // 50 MB
        maxRequestSize = 1024 * 1024 * 100)      // 100 MB

public class UploadVideoServlet extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        Part binaryFile = request.getPart("binaryFile");
        InputStream binaryFileInputStream = binaryFile.getInputStream();
        Part metadata = request.getPart("metadata");
        InputStream metadataInputStream = metadata.getInputStream();
        /*VideoUploadTimeMetaData videoUploadTimeMetaData = new GsonBuilder().setPrettyPrinting().create()
                .fromJson(new InputStreamReader(metadataInputStream),
                        VideoUploadTimeMetaData.class);
        try {
            Video video = new VideoBuilder()
                    .setFormat(videoUploadTimeMetaData.getFileExtension())
                    .setName(videoUploadTimeMetaData.getFileName())
                    .setVideoCategory(videoUploadTimeMetaData.getVideoCategory())
                    .setComments(FXCollections.observableArrayList())
                    .setLikes(FXCollections.observableArrayList())
                    .setUser(SQLUtils.getUserByUsername(videoUploadTimeMetaData.getUsername()))
                    .createVideo();
            SQLUtils.insertVideo(video);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File file = new File(
                "E:\\TutorsPoint\\uploaded\\" + videoUploadTimeMetaData.getFileName() + "." +
                        videoUploadTimeMetaData.getFileExtension());
        copy(binaryFileInputStream, new FileOutputStream(file));*/
        Video video = new GsonBuilder().setPrettyPrinting().create()
                .fromJson(new InputStreamReader(metadataInputStream), Video.class);
        try {
            SQLUtils.insertVideo(video);
            video.setId(SQLUtils.getVideoIdByVideoName(video.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        File file = new File("E\\TutorsPoint\\uploaded\\" + video.getId());
        copy(binaryFileInputStream, new FileOutputStream(file));
        response.getWriter().print(0);
    }

    public static final void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        int chr;
        while ((chr = inputStream.read()) != -1) {
            outputStream.write(chr);
        }
    }
}
