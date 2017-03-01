package com.strekha.lastfm.presenter;

import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.model.ArtistInfoModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.InfoView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistInfoPresenter{

    private ArtistInfoModel mModel;
    private InfoView mView;
    private Subscription mCachedDataSubscription;
    private Subscription mFreshDataSubscription;

    public ArtistInfoPresenter(ArtistInfoModel model) {
        mModel = model;
    }

    public void requestData(String artist) {
        getViewState().showProgress();
        mCachedDataSubscription = mModel
                .requestCachedData(artist)
                .filter(info -> info != null)
                .switchIfEmpty(mModel.requestFreshData(artist))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }


    public void requestFreshData(String artist) {
        unsubscribe(mFreshDataSubscription);
        mFreshDataSubscription = mModel
                .requestFreshData(artist)
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
        else getViewState().showErrorMessage(error.getMessage());
    }

    private InfoView getViewState() {
        return mView;
    }

    public void setViewState(InfoView view) {
        mView = view;
    }

    public void onDestroyView() {
        unsubscribe(mCachedDataSubscription);
        unsubscribe(mFreshDataSubscription);
    }

    private void unsubscribe(Subscription subscription){
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
