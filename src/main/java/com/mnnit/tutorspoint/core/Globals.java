package com.mnnit.tutorspoint.core;

import com.google.gson.Gson;
import org.hildan.fxgson.FxGson;

public class Globals {

    public static final Gson GSON = FxGson.fullBuilder().serializeNulls().setPrettyPrinting().create();
}
