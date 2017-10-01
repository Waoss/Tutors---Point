package com.mnnit.tutorspoint.net;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class BooleanResponseTask extends HttpURLTask<Boolean> {

    private static final Logger LOGGER = Logger.getLogger(BooleanResponseTask.class.getName());

    public BooleanResponseTask(final URL url) throws IOException {
        super(url);
    }

    @Override
    protected Boolean call() throws Exception {
        LOGGER.info("Sending request @ URL");
        LOGGER.info(getUrl().toString());
        return IOUtils.toString(new InputStreamReader(url.openStream())).equals("0");
    }
}
