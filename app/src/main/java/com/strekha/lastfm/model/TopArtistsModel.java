package com.strekha.lastfm.model;

import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.entity.top.TopArtists;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Observable;

public class TopArtistsModel {

    public static final String TOP_ARTISTS_TAG = "top_artist_tag";

    private LastFM mLastFM;
    private DatabaseHelper mDatabase;

    public TopArtistsModel(LastFM lastFM, DatabaseHelper database) {
        mLastFM = lastFM;
        mDatabase = database;
    }

    public Observable<List<Artist>> requestFreshData() {
            return mLastFM.getTopArtists()
                    .cache()
                    .retry(1)
                    .doOnNext(json -> mDatabase.writeJson(TopArtistsModel.TOP_ARTISTS_TAG, json))
                    .map(this::getArtist);
    }

    public Observable<List<Artist>> requestCachedData() {

        return Observable.fromCallable(() ->
        {
            String json = mDatabase.readJson(TOP_ARTISTS_TAG);
            return json != null ? getArtist(json) : null;
        });
    }

    private List<Artist> getArtist(String json){
        return JsonParser.parse(TopArtists.class, json).getArtists();
    }
}
