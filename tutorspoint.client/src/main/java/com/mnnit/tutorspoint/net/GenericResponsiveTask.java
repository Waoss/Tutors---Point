package com.mnnit.tutorspoint.net;

import com.mnnit.tutorspoint.core.Globals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;

public class GenericResponsiveTask<V> extends HttpURLTask<V> {
    private Type type;

    public GenericResponsiveTask(final URL url, final Type type) throws IOException {
        super(url);
        this.type = type;
    }

    @Override
    protected V call() throws Exception {
        return Globals.GSON.fromJson(new InputStreamReader(httpURLConnection.getInputStream()), type);
    }
}
