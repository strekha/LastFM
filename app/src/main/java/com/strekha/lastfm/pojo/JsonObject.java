package com.strekha.lastfm.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class JsonObject extends RealmObject {

    public static final String TAG = "tag";

    @PrimaryKey
    private String tag;
    private String json;

    public String getTag() {
        return tag;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
