package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.ListView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TopArtistsListPresenter extends MvpPresenter<ListView> {

    private TopArtistsModel mModel = new TopArtistsModel();

    public void requestData() {
        getViewState().showProgress();
        mModel.requestCachedData()
                .filter(list -> list != null)
                .switchIfEmpty(mModel.requestFreshData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }


    public void requestFreshData() {
        mModel.requestFreshData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }

    private void setData(List<Artist> artists){
        getViewState().setData(artists);
        getViewState().hideProgress();
    }

    private void handleError(Throwable error){
        if (!NetworkChangeReceiver.isNetworkAvailable())
            getViewState().showNetworkIsNotAvailable();
        else getViewState().handleError(error.getMessage());
    }
}
