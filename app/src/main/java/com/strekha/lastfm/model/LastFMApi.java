package com.strekha.lastfm.model;

import com.strekha.lastfm.model.content.info.Artist;
import com.strekha.lastfm.model.content.info.ArtistInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFMApi {

    @GET("?method=chart.gettopartists&api_key=3c6754afec9ea1df32d686f9434b1f31&format=json")
    Observable<List<Artist>> getTopArtists();

    @GET("?method=artist.getinfo&api_key=3c6754afec9ea1df32d686f9434b1f31&format=json")
    Observable<ArtistInfo> getArtistInfo(@Query("artist") String artist);
}
