package com.strekha.lastfm.di;


import com.strekha.lastfm.model.ArtistInfoModel;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.network.LastFM;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistInfoModule {

    @Provides
    @Singleton
    ArtistInfoPresenter providePresenter(ArtistInfoModel model){
        return new ArtistInfoPresenter(model);
    }

    @Provides
    @Singleton
    ArtistInfoModel provideModel(LastFM apiService, DatabaseHelper database){
        return new ArtistInfoModel(apiService, database);
    }
}
