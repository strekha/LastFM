package com.strekha.lastfm.presenter;

import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.LastFMApi;
import com.strekha.lastfm.POJO.info.ArtistInfo;
import com.strekha.lastfm.presenter.interfaces.InfoPresenter;
import com.strekha.lastfm.view.interfaces.InfoView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistInfoPresenter implements InfoPresenter {

    private LastFMApi lastFM;
    private InfoView view;

    @Override
    public void getData(String title, String lang) {
        if (lastFM == null) lastFM = new LastFM();
        if (!view.isNetworkAvailable()) {
            view.showNetworkIsNotAvailable();
            return;
        }
        Observable<ArtistInfo> artistInfo = lastFM.getArtistInfo(title, lang);
        artistInfo.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        information -> view.setInfo(information),
                        error -> view.makeToast(error.getMessage()));
    }

    @Override
    public void bindView(InfoView view) {
        this.view = view;
    }
}
