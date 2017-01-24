package com.strekha.lastfm;

import android.app.Application;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

public class LastFmApplication extends Application {

    private static LastFmApplication sApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        Realm.init(this);
        sApplication = this;
    }

    public static LastFmApplication getInstance(){
        return sApplication;
    }

    public void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
