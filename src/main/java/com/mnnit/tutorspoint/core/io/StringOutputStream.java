package com.mnnit.tutorspoint.core.io;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {
    private StringBuffer buffer = new StringBuffer();

    @Override
    public void write(final int b) throws IOException {
        buffer.append((char) b);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
