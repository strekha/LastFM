package com.strekha.lastfm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.strekha.lastfm.di.AppComponent;
import com.strekha.lastfm.di.DaggerAppComponent;
import com.strekha.lastfm.di.TopArtistsModule;

import io.realm.Realm;

public class LastFmApplication extends Application {

    private static LastFmApplication sApplication;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        initRealm();
        sApplication = this;
        sAppComponent = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .topArtistsModule(new TopArtistsModule())
                .build();
    }

    public static LastFmApplication getInstance(){
        return sApplication;
    }

    public AppComponent getAppComponent(){
        return sAppComponent;
    }

    protected void initRealm(){
        Realm.init(this);
    }
}
