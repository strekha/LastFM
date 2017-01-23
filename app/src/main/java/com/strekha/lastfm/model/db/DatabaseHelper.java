package com.strekha.lastfm.model.db;

import com.strekha.lastfm.pojo.JsonObject;

import io.realm.Realm;
import rx.Observable;

public class DatabaseHelper {

    private static DatabaseHelper mHelper;

    private DatabaseHelper(){}

    public static DatabaseHelper getInstance(){
        if(mHelper == null) mHelper = new DatabaseHelper();
        return mHelper;
    }

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
            JsonObject object = jsonObject[0];
            realm.executeTransaction(realm1 -> {
                jsonObject[0] = findInRealm(tag, realm1);
                if (jsonObject[0] != null) jsonObject[0] = realm1.copyFromRealm(jsonObject[0]);
            });
            realm.close();
            if (object != null) return object.getJson();
            else return null;
    }

    private JsonObject findInRealm(String tag, Realm realm){
        return realm.where(JsonObject.class).equalTo(JsonObject.TAG, tag).findFirst();
    }
}
