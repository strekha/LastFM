package com.strekha.lastfm.model;


import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;
import com.strekha.lastfm.pojo.info.ArtistInfo;
import com.strekha.lastfm.pojo.info.Artist;
import com.strekha.lastfm.pojo.top.TopArtists;

import java.util.List;

import rx.Observable;

public class ArtistInfoModel {

    private LastFM mLastFM = LastFM.getInstance();

    public Observable<Artist> requestFreshData(String artist, String lang) {
        return mLastFM.getArtistInfo(artist, lang)
                .cache()
                .retry(1)
                .doOnNext(json -> DatabaseHelper.getInstance().writeJson(artist, json))
                .map(json -> JsonParser.parse(ArtistInfo.class, json).getArtist());
    }

    public Observable<Artist> requestCachedData(String artist) {

        return Observable.fromCallable(() ->
        {
            String json = DatabaseHelper.getInstance().readJson(artist);
            if (json == null) return null;
            return JsonParser.parse(ArtistInfo.class, json).getArtist();
        });
    }
}
