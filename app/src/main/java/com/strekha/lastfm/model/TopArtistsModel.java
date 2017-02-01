package com.strekha.lastfm.model;

import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;
import com.strekha.lastfm.pojo.top.Artist;
import com.strekha.lastfm.pojo.top.TopArtists;

import java.util.List;

import rx.Observable;

public class TopArtistsModel {

    public static final String TOP_ARTISTS_TAG = "top_artist_tag";

    private LastFM mLastFM = LastFM.getInstance();

    public Observable<List<Artist>> requestFreshData() {
            return mLastFM.getTopArtists()
                    .cache()
                    .retry(1)
                    .doOnNext(json -> DatabaseHelper.getInstance().writeJson(TopArtistsModel.TOP_ARTISTS_TAG, json))
                    .map(json -> JsonParser.parse(TopArtists.class, json).getArtists());
    }

    public Observable<List<Artist>> requestCachedData() {

        return Observable.fromCallable(() ->
        {
            String json = DatabaseHelper.getInstance().readJson(TOP_ARTISTS_TAG);
            if (json == null) return null;
            return JsonParser.parse(TopArtists.class, json).getArtists();
        });
    }


}
