package com.strekha.lastfm.presenter;

import android.util.Log;

import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.LastFMApi;
import com.strekha.lastfm.model.top.Artist;
import com.strekha.lastfm.model.top.ArtistComparator;
import com.strekha.lastfm.model.top.TopArtists;
import com.strekha.lastfm.view.View;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListActivityPresenter implements ListPresenter {

    private View view;
    private List<Artist> artists;
    private LastFMApi lastFM;

    public ListActivityPresenter(View view) {
        this.view = view;
        Log.d("myLog", view.toString());
    }

    @Override
    public void getData() {
        if (lastFM == null) {
            lastFM = new LastFM();
            Log.d("myLog", "create lastfmAPI");
        }
        Observable<TopArtists> artistInfo = lastFM.getTopArtists();
        artistInfo.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopArtists>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.makeToast(e.getMessage());
                    }

                    @Override
                    public void onNext(TopArtists topArtists) {
                        artists = topArtists.getArtists().getArtist();
                        Collections.sort(artists, new ArtistComparator());
                        Log.d("mylog", artists.get(0).getName());
                        view.setData(artists);
                    }
                });
    }

    @Override
    public int onItemClickListener() {
        return 0;
    }
}
