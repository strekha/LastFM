package com.strekha.lastfm.di;

import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.network.LastFM;
import com.strekha.lastfm.presenter.TopArtistsListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TopArtistsModule {

    @Singleton
    @Provides
    TopArtistsListPresenter providePresenter(TopArtistsModel model){
        return new TopArtistsListPresenter(model);
    }

    @Singleton
    @Provides
    TopArtistsModel provideModel(LastFM lastFM, DatabaseHelper database){
        return new TopArtistsModel(lastFM, database);
    }
}
