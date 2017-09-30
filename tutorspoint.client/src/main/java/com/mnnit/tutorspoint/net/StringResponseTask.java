package com.mnnit.tutorspoint.net;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StringResponseTask extends HttpURLTask<String> {
    public StringResponseTask(final URL url) throws IOException {
        super(url);
    }

    @Override
    protected String call() throws Exception {
        return IOUtils.toString(new InputStreamReader(httpURLConnection.getInputStream()));
    }
}
