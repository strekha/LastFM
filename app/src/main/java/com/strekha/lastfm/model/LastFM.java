package com.strekha.lastfm.model;

import com.strekha.lastfm.model.content.info.Artist;
import com.strekha.lastfm.model.content.info.ArtistInfo;
import com.strekha.lastfm.model.top.TopArtists;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
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
    public Observable<ArtistInfo> getArtistInfo(@Path("artist") String artist) {
        return api.getArtistInfo(artist);
    }
}
