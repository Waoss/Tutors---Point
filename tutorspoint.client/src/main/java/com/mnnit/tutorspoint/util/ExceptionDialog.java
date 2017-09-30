package com.mnnit.tutorspoint.util;

import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog extends Dialog {
    public ExceptionDialog(final Throwable throwable) {
        final TextArea textArea = new TextArea();
        final AnchorPane graphic = new AnchorPane();
        setGraphic(graphic);
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        textArea.setText(stringWriter.toString());
    }
}
