package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.model.ArtistInfoModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.InfoView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ArtistInfoPresenter extends MvpPresenter<InfoView> {

    private ArtistInfoModel mModel = new ArtistInfoModel();

    public void requestData(String artist, String lang) {
        getViewState().showProgress();
        mModel.requestCachedData(artist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        info -> {
                            if (info == null) requestFreshData(artist, lang);
                            else {
                                getViewState().setInfo(info);
                                getViewState().hideProgress();
                            }
                        },
                        error -> getViewState().handleError(error.getMessage())
                );
    }


    public void requestFreshData(String artist, String lang) {
        if (!NetworkChangeReceiver.isNetworkAvailable()) {
            getViewState().showNetworkIsNotAvailable();
            return;
        }
        mModel.requestFreshData(artist, lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        info -> getViewState().setInfo(info),
                        error -> getViewState().handleError(error.getMessage()));
        getViewState().hideProgress();
    }
}
