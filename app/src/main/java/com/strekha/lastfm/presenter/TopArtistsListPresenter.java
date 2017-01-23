package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.ListView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class TopArtistsListPresenter extends MvpPresenter<ListView> {

    private TopArtistsModel mModel = new TopArtistsModel();

    public void requestData() {
        getViewState().showProgress();
        mModel.requestCachedData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        artists -> {
                            if (artists == null) requestFreshData();
                            else {
                                getViewState().setData(artists);
                                getViewState().hideProgress();
                            }
                        }
                );
    }


    public void requestFreshData() {
        if (!NetworkChangeReceiver.isNetworkAvailable()) {
            getViewState().showNetworkIsNotAvailable();
            return;
        }
        mModel.requestFreshData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artists -> getViewState().setData(artists));
        getViewState().hideProgress();
    }
}
