package com.strekha.lastfm.model;

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

    //Тут напутал с патоками работа с бд происходит в main thread.
    public Observable<String> readJson(String tag){
        Realm realm = Realm.getDefaultInstance();
        final JsonObject[] jsonObject = new JsonObject[1];
        realm.executeTransaction(realm1 -> {
            jsonObject[0] = findInRealm(tag, realm1);
            if (jsonObject[0] != null) jsonObject[0] = realm1.copyFromRealm(jsonObject[0]);
        });
        realm.close();
        if (jsonObject[0] != null) return Observable.just(jsonObject[0].getJson());
            else return Observable.just(null);
    }

    private JsonObject findInRealm(String tag, Realm realm){
        return realm.where(JsonObject.class).equalTo(JsonObject.TAG, tag).findFirst();
    }
}
