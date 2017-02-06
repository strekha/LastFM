package com.strekha.lastfm.model;


import java.util.Optional;

import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.entity.info.ArtistInfo;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;

import rx.Observable;

public class ArtistInfoModel {

    private LastFM mLastFM = LastFM.getInstance();

    public Observable<Artist> requestFreshData(String artist) {
        return mLastFM.getArtistInfo(artist)
                .cache()
                .retry(1)
                .doOnNext(json -> DatabaseHelper.getInstance().writeJson(artist, json))
                .map(json -> JsonParser.parse(ArtistInfo.class, json).getArtist());
    }

    public Observable<Artist> requestCachedData(String artist) {
        return Observable.fromCallable(() ->
        {
            String json = DatabaseHelper.getInstance().readJson(artist);
            return json != null ? getArtist(json) : null;
        });
    }

    private Artist getArtist(String j) {
        return JsonParser.parse(ArtistInfo.class, j).getArtist();
    }
}
