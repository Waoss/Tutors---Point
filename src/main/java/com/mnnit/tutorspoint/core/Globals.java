package com.mnnit.tutorspoint.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Globals {

    public static final Gson GSON = new GsonBuilder().serializeNulls().create();
}
