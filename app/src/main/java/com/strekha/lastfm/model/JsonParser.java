package com.strekha.lastfm.model;

import com.google.gson.Gson;

public class JsonParser {

    private JsonParser(){}

    public static<T> T parse(Class<T> clazz, String json){
        return new Gson().fromJson(json, clazz);
    }
}
