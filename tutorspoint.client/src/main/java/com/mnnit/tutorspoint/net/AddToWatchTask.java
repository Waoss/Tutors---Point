package com.mnnit.tutorspoint.net;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ProtocolException;
import java.net.URL;

public class AddToWatchTask extends HttpURLTask<String> {

    public AddToWatchTask(final URL url) throws IOException {
        super(url);
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
