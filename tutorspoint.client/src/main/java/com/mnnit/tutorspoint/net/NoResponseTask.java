package com.mnnit.tutorspoint.net;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NoResponseTask extends HttpURLTask<Boolean> {

    public NoResponseTask(final URL url) throws IOException {
        super(url);
    }

    @Override
    protected Boolean call() throws Exception {
        return IOUtils.toString(new InputStreamReader(url.openStream())).equals("0");
    }
}
