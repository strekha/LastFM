package com.strekha.lastfm.presenter;

import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.LastFMApi;
import com.strekha.lastfm.model.content.info.ArtistInfo;
import com.strekha.lastfm.view.InfoView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistInfoPresenter implements InfoPresenter {

    private LastFMApi lastFM;
    private InfoView view;

    @Override
    public void getData(String title) {
        if (lastFM == null) lastFM = new LastFM();
        Observable<ArtistInfo> artistInfo = lastFM.getArtistInfo(title);
        artistInfo.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(information -> {
                    view.setInfo(information);
                });
    }

    @Override
    public void bindView(InfoView view) {
        this.view = view;
    }
}
