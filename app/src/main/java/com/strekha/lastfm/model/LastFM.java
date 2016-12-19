package com.strekha.lastfm.model;

import com.strekha.lastfm.POJO.info.ArtistInfo;
import com.strekha.lastfm.POJO.top.TopArtists;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import rx.Observable;

public class LastFM implements LastFMApi {

    Retrofit retrofit;
    LastFMApi api;

    public LastFM() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .build();
        api = retrofit.create(LastFMApi.class);
    }

    public Observable<TopArtists> getTopArtists() {
        return api.getTopArtists();
    }

    @Override
    public Observable<ArtistInfo> getArtistInfo(@Query("artist") String artist, @Query("lang") String lang) {
        return api.getArtistInfo(artist, lang);
    }
}
