package com.mnnit.tutorspoint.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.property.Property;

public class Globals {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
