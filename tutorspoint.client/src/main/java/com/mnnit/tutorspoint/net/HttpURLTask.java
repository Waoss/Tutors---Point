package com.mnnit.tutorspoint.net;

import javafx.concurrent.Task;

import java.net.*;

public abstract class HttpURLTask<T> extends Task<T> {
    public abstract URL getURL();

    public abstract void setURL(final URL url);

    public abstract HttpURLConnection getHttpURLConnection();

    public abstract void setRequestMethod(final String method) throws ProtocolException;

}
