package com.mnnit.tutorspoint.net;


import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertCategoryTask extends BooleanResponseTask {
    public InsertCategoryTask(final String name, final String parent) throws IOException {
        super(new URL(System.getProperty("com.mnnit.tutorspoint.server.url")
                + "/insertCategory?name=" + URLEncoder.encode(name, "utf-8") + "&parent="
                + URLEncoder.encode(parent, "utf-8")));
    }

    @Override
    protected Boolean call() throws Exception {
        return super.call();
    }
}
