package com.mnnit.tutorspoint.net;

import javafx.concurrent.Task;

import java.io.IOException;
import java.net.*;

public abstract class HttpURLTask<T> extends Task<T> {

    protected URL url;
    protected HttpURLConnection httpURLConnection;

    public HttpURLTask(final URL url) throws IOException {
        this.url = url;
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(final URL url) {
        this.url = url;
    }

    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    public void setHttpURLConnection(final HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }

    public abstract void setRequestMethod(final String method) throws ProtocolException;

}
