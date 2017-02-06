package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.model.ArtistInfoModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.InfoView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ArtistInfoPresenter extends MvpPresenter<InfoView> {

    private ArtistInfoModel mModel = new ArtistInfoModel();

    public void requestData(String artist) {
        getViewState().showProgress();
        mModel.requestCachedData(artist)
                .filter(info -> info != null)
                .switchIfEmpty(mModel.requestFreshData(artist))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }


    public void requestFreshData(String artist) {
        mModel.requestFreshData(artist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }

    private void setData(Artist artist){
        getViewState().setInfo(artist);
        getViewState().hideProgress();
    }

    private void handleError(Throwable error){
        if (!NetworkChangeReceiver.isNetworkAvailable())
            getViewState().showNetworkIsNotAvailable();
        else getViewState().handleError(error.getMessage());
    }
}
