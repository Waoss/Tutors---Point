package com.mnnit.tutorspoint.core.video;

import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.*;
import java.nio.file.Files;

class VideoUploader {

    final String CRLF = "\r\n";
    String url = "http://localhost:9000/uploadvideo";
    String charset = "utf-8";
    File fileToUpload = new File("");
    String boundary = Long.toHexString(System.nanoTime());
    HttpURLConnection httpURLConnection;
    Video video;

    public void upload() throws Exception {
        init();
        final OutputStream outputStream = httpURLConnection.getOutputStream();
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
        sendNormalParam(printWriter, "metadata",
                serializeVideo());
        sendBinaryFile(printWriter, outputStream, fileToUpload);
        System.out.println(httpURLConnection.getResponseCode());
    }

    private void init() throws Exception {
        httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    }

    private void sendNormalParam(PrintWriter writer, String param, String value) {
        writer.append("--" + boundary).append(CRLF);
        writer.append("Content-Disposition: form-data; name=\"" + param + "\"").append(CRLF);
        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
        writer.append(CRLF).append(value).append(CRLF).flush();
    }

    public String serializeVideo() throws IOException {
        return new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Video.class, new VideoTypeAdapter()).create()
                .toJson(video);

    }

    private void sendBinaryFile(PrintWriter writer, OutputStream outputStream, File file) throws IOException {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(final String charset) {
        this.charset = charset;
    }

    public File getFileToUpload() {
        return fileToUpload;
    }

    public void setFileToUpload(final File fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(final String boundary) {
        this.boundary = boundary;
    }

    public String getCRLF() {
        return CRLF;
    }

    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    public void setHttpURLConnection(final HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(final Video video) {
        this.video = video;
    }
}
