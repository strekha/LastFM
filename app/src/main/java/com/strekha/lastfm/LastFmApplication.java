package com.strekha.lastfm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

public class LastFmApplication extends Application {

    private static Application sApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Realm.init(this);
        sApplication = this;
    }

    public static Application getInstance(){
        return sApplication;
    }
}
