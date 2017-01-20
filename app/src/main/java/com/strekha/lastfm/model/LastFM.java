package com.strekha.lastfm.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

public class LastFM {

    private static final String API_KEY = "3c6754afec9ea1df32d686f9434b1f31";
    private static final String BASE_URL = "http://ws.audioscrobbler.com/2.0/";

    private LastFMApi mApi;
    private static LastFM mLastFM;

    public static LastFM getInstance(){
        if (mLastFM == null) mLastFM = new LastFM();
        return mLastFM;
    }

    private LastFM() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApi = retrofit.create(LastFMApi.class);
    }

    public Observable<String> getTopArtists() {
        return mApi.getTopArtists(API_KEY);
    }

    public Observable<String> getArtistInfo(String artist, String lang) {
        return mApi.getArtistInfo(artist, lang, API_KEY);
    }
}
