package com.strekha.lastfm.model;


import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.entity.info.ArtistInfo;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import rx.Observable;

public class ArtistInfoModel {

    private LastFM mLastFM;
    private DatabaseHelper mDatabase;

    public ArtistInfoModel(LastFM lastFM, DatabaseHelper database) {
        mLastFM = lastFM;
        mDatabase = database;
    }

    public Observable<Artist> requestFreshData(String artist) {
        return mLastFM.getArtistInfo(artist)
                .cache()
                .retry(1)
                .doOnNext(json -> mDatabase.writeJson(artist, json))
                .map(json -> JsonParser.parse(ArtistInfo.class, json).getArtist());
    }

    public Observable<Artist> requestCachedData(String artist) {
        return Observable.fromCallable(() ->
        {
            String json = mDatabase.readJson(artist);
            return json != null ? getArtist(json) : null;
        });
    }

    private Artist getArtist(String j) {
        return JsonParser.parse(ArtistInfo.class, j).getArtist();
    }
}
