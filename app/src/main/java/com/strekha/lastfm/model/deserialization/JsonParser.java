package com.strekha.lastfm.model.deserialization;

import com.google.gson.Gson;

public class JsonParser {

    private static Gson gson = new Gson();

    private JsonParser(){}

    public static<T> T parse(Class<T> clazz, String json){
        return gson.fromJson(json, clazz);
    }
}
