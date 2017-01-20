package com.strekha.lastfm.model;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFMApi {

    @GET("?method=chart.gettopartists&format=json")
    Observable<String> getTopArtists(@Query("api_key") String api_key);

    @GET("?method=artist.getinfo&format=json")
    Observable<String> getArtistInfo(@Query("artist") String artist, @Query("lang") String lang,
                                     @Query("api_key") String api_key);
}
