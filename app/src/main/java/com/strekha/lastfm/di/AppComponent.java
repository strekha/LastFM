package com.strekha.lastfm.di;

import com.strekha.lastfm.view.ArtistInfoActivity;
import com.strekha.lastfm.view.ListActivity;
import com.strekha.lastfm.view.interfaces.InfoView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TopArtistsModule.class, ArtistInfoModule.class, UtilsModule.class})
public interface AppComponent {
    void inject(ListActivity activity);
    void inject(ArtistInfoActivity activity);
}
