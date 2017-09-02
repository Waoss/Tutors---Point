package com.mnnit.tutorspoint.core;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import org.junit.Test;

public class PropertyTypeAdapterTest {

    @Test
    public void write() {
        Globals.GSON.toJson(new SimpleListProperty<String>(FXCollections.observableArrayList("asd")));
    }
}
