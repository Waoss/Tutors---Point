package com.mnnit.tutorspoint.core.video;

import javafx.beans.property.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import static com.mnnit.tutorspoint.core.Globals.GSON;

public class VideoUploader {

    private SimpleObjectProperty<Video> video = new SimpleObjectProperty<>();
    private SimpleStringProperty videoJson = new SimpleStringProperty(GSON.toJson(video));
    private SimpleStringProperty url = new SimpleStringProperty();
    private StringProperty charset = new ReadOnlyStringWrapper("utf-8");
    private SimpleObjectProperty<URLConnection> urlConnection = new SimpleObjectProperty<>(
            new URL(url.get()).openConnection());
    private SimpleStringProperty boundary = new SimpleStringProperty(Long.toHexString(System.nanoTime()));
    private SimpleObjectProperty<File> file = new SimpleObjectProperty<>();
    private SimpleObjectProperty<PrintWriter> writer = new SimpleObjectProperty<>();
    private SimpleObjectProperty<OutputStream> output = new SimpleObjectProperty<>();
    public static final String CRLF = "\r\n";

    {
        video.addListener((observable, oldValue, newValue) -> videoJson.set(GSON.toJson(newValue)));
        output.addListener((observable, oldValue, newValue) -> {
            try {
                writer.set(new PrintWriter(new OutputStreamWriter(newValue, charset.get()), true));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
    }

    public VideoUploader() throws Exception {
    }

    private void initRequest() throws IOException {
        getUrlConnection().setDoOutput(true);
        getUrlConnection().setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        writer.set(new PrintWriter(new OutputStreamWriter(getUrlConnection().getOutputStream(), charset.get()), true));
    }

    public void sendRequest() throws IOException {
        initRequest();
        sendMetadata();
        sendActualFile();
    }

    private void sendActualFile() throws IOException {
        PrintWriter writer = getWriter();
        writer.append("--" + boundary).append(CRLF);
        writer.append(
                "Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + file.get().getName() + "\"").append(
                CRLF);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(file.get().getName())).append(CRLF);
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        Files.copy(file.get().toPath(), output.get());
        output.get().flush(); // Important before continuing with writer!
        writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

        // End of multipart/form-data.
        writer.append("--" + boundary + "--").append(CRLF).flush();
    }

    private void sendMetadata() throws IOException {
        sendBasicRequest("metadata", file.getName(), videoJson.get());
    }

    private void sendBasicRequest(final String metadata, final String fileName, final String content) throws
            IOException {
        PrintWriter writer = getWriter();
        writer.append("--" + getBoundary()).append(CRLF);
        writer.append(
                "Content-Disposition: form-data; name=\"" + metadata + "\"; filename=\"" + fileName + "\"").append(
                CRLF);
        writer.append("Content-Type: text/plain; charset=" + charset.get()).append(CRLF);
        writer.append(CRLF).flush();
        writer.append(content).append(CRLF);
        output.get().flush();
        writer.append(CRLF).flush();
    }

    public Video getVideo() {
        return video.get();
    }

    public SimpleObjectProperty<Video> videoProperty() {
        return video;
    }

    public void setVideo(final Video video) {
        this.video.set(video);
    }

    public String getVideoJson() {
        return videoJson.get();
    }

    public SimpleStringProperty videoJsonProperty() {
        return videoJson;
    }

    public void setVideoJson(final String videoJson) {
        this.videoJson.set(videoJson);
    }

    public String getUrl() {
        return url.get();
    }

    public SimpleStringProperty urlProperty() {
        return url;
    }

    public void setUrl(final String url) {
        this.url.set(url);
    }

    public String getCharset() {
        return charset.get();
    }

    public StringProperty charsetProperty() {
        return charset;
    }

    public void setCharset(final String charset) {
        this.charset.set(charset);
    }

    public URLConnection getUrlConnection() {
        return urlConnection.get();
    }

    public SimpleObjectProperty<URLConnection> urlConnectionProperty() {
        return urlConnection;
    }

    public void setUrlConnection(final URLConnection urlConnection) {
        this.urlConnection.set(urlConnection);
    }

    public String getBoundary() {
        return boundary.get();
    }

    public SimpleStringProperty boundaryProperty() {
        return boundary;
    }

    public void setBoundary(final String boundary) {
        this.boundary.set(boundary);
    }

    public File getFile() {
        return file.get();
    }

    public SimpleObjectProperty<File> fileProperty() {
        return file;
    }

    public void setFile(final File file) {
        this.file.set(file);
    }

    public PrintWriter getWriter() {
        return writer.get();
    }

    public SimpleObjectProperty<PrintWriter> writerProperty() {
        return writer;
    }

    public void setWriter(final PrintWriter writer) {
        this.writer.set(writer);
    }
}
