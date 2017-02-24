package com.strekha.lastfm.di;

import com.strekha.lastfm.view.ListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class, UtilsModule.class})
public interface AppComponent {
    void inject(ListActivity activity);
}
