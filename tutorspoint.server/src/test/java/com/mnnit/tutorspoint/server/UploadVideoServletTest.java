package com.mnnit.tutorspoint.server;

import com.google.gson.GsonBuilder;
import com.mnnit.tutorspoint.core.video.Video;
import com.mnnit.tutorspoint.core.video.VideoBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

public class UploadVideoServletTest {
    final String CRLF = "\r\n";
    String url = "http://localhost:9000/uploadvideo";
    String charset = "utf-8";
    File toUpload = new File("E:\\song videos\\galiway.mp4");
    String boundary = Long.toHexString(System.nanoTime());
    HttpURLConnection httpURLConnection;

    @Before
    public void init() throws Exception {
        httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    }

    @Test
    public void doPost() throws Exception {
        final OutputStream outputStream = httpURLConnection.getOutputStream();
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
        sendNormalParam(printWriter, "metadata",
                new GsonBuilder().setPrettyPrinting().create().toJson(new VideoUploadTimeMetaData(toUpload.getName(),
                        toUpload.toString().substring(toUpload.toString().lastIndexOf(".")))));
        sendBinaryFile(printWriter, outputStream, toUpload);
        System.out.println(httpURLConnection.getResponseCode());
    }

    public void sendNormalParam(PrintWriter writer, String param, String value) {
        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"" + param + "\"").append(CRLF);
        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
        writer.append(CRLF).append(value).append(CRLF).flush();
    }

    public void sendBinaryFile(PrintWriter writer, OutputStream outputStream, File file) throws IOException {
        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + file.getName() + "\"")
                .append(CRLF);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName())).append(CRLF);
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        Files.copy(file.toPath(), outputStream);
        outputStream.flush(); // Important before continuing with writer!
        writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
        // End of multipart/form-data.
        writer.append("--" + boundary + "--").append(CRLF).flush();
    }

    @Test
    public void testupload() throws Exception {
        Video video = new VideoBuilder().createVideo();
        video.setName("byte");
        video.setFormat("mp4");
        Video.upload(video, new File("E:\\song videos\\byte.mp4"));
    }

}