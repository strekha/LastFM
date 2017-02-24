package com.strekha.lastfm.di;

import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.network.LastFM;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    LastFM provideLastFmApi(){
        return new LastFM();
    }

    @Singleton
    @Provides
    DatabaseHelper provideDatabaseHelper(){
        return new DatabaseHelper();
    }
}
