package com.strekha.lastfm.model.db;

import com.strekha.lastfm.entity.JsonObject;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;

public class DatabaseHelper {

    public void writeJson(String tag, String json){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            JsonObject jsonObject = findInRealm(tag, realm1);
            if (jsonObject == null) jsonObject = realm1.createObject(JsonObject.class, tag);
            jsonObject.setJson(json);
        });
        realm.close();
    }

    public String readJson(String tag){
            Realm realm = Realm.getDefaultInstance();
            final JsonObject[] jsonObject = new JsonObject[1];
            realm.executeTransaction(realm1 -> {
                jsonObject[0] = findInRealm(tag, realm1);
                if (jsonObject[0] != null) jsonObject[0] = realm1.copyFromRealm(jsonObject[0]);
            });
            realm.close();
            if (jsonObject[0] != null) return jsonObject[0].getJson();
            else return null;
    }

    private JsonObject findInRealm(String tag, Realm realm){
        return realm.where(JsonObject.class).equalTo(JsonObject.TAG, tag).findFirst();
    }
}
