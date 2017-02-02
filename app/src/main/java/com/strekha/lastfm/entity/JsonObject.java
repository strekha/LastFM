package com.strekha.lastfm.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonObject that = (JsonObject) o;

        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
        return json != null ? json.equals(that.json) : that.json == null;

    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (json != null ? json.hashCode() : 0);
        return result;
    }
}
