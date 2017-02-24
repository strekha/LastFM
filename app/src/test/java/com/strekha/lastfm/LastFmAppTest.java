package com.strekha.lastfm;


import com.strekha.lastfm.di.AppComponent;
import com.strekha.lastfm.di.DaggerAppComponent;
import com.strekha.lastfm.di.TopArtistsTestModule;


public class LastFmAppTest extends LastFmApplication {

    @Override
    protected void initRealm() {
        //do nothing
    }

    /**
     * This component will inject mocked presenter to activity.
     */
    @Override
    public AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .topArtistsModule(new TopArtistsTestModule())
                .build();
    }
}
