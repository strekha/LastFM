package com.strekha.lastfm.presenter;


import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.LastFMApi;
import com.strekha.lastfm.POJO.top.Artist;
import com.strekha.lastfm.POJO.top.ArtistComparator;
import com.strekha.lastfm.POJO.top.TopArtists;
import com.strekha.lastfm.presenter.interfaces.ListPresenter;
import com.strekha.lastfm.view.interfaces.ListView;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListActivityPresenter implements ListPresenter {

    private ListView view;
    private List<Artist> artists;
    private LastFMApi lastFM;

    @Override
    public void getData() {
        if (lastFM == null) lastFM = new LastFM();
        if (!view.isNetworkAvailable()) {
            view.showNetworkIsNotAvailable();
            return;
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
                        view.setData(artists);
                    }
                });
    }

    @Override
    public void bindView(ListView view) {
        this.view = view;
    }
}
