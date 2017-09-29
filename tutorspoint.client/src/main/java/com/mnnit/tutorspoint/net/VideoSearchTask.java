package com.mnnit.tutorspoint.net;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class VideoSearchTask extends HttpURLTask<String> {

    private URL url;
    private HttpURLConnection httpURLConnection;

    public VideoSearchTask(URL url) {
        try {
            this.url = url;
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public URL getURL() {
        return url;
    }

    @Override
    public void setURL(final URL url) {
        this.url = url;
    }

    @Override
    public HttpURLConnection getHttpURLConnection() {
        return httpURLConnection;
    }

    @Override
    public void setRequestMethod(final String method) throws ProtocolException {
        httpURLConnection.setRequestMethod(method);
    }

    @Override
    protected String call() throws Exception {
        return IOUtils.toString(new InputStreamReader(httpURLConnection.getInputStream()));
    }
}
