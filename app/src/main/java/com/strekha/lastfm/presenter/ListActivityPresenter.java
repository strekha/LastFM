package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.POJO.top.TopArtists;
import com.strekha.lastfm.model.DatabaseHelper;
import com.strekha.lastfm.model.JsonParser;
import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.view.interfaces.ListView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ListActivityPresenter extends MvpPresenter<ListView> {

    private static final String TOP_ARTISTS_TAG = "top_artisst_tag";

    private LastFM mLastFM = LastFM.getInstance();

    public void getFreshData() {
        getViewState().showProgress();
        mLastFM.getTopArtists()
                .subscribeOn(Schedulers.io())
                .retry(1)
                .observeOn(Schedulers.computation())
                .doOnNext(json -> DatabaseHelper.getInstance().writeJson(TOP_ARTISTS_TAG, json))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        json -> {
                            getViewState().setData(JsonParser
                                    .parse(TopArtists.class, json)
                                    .getArtists());
                            getViewState().hideProgress();
                        },
                        error -> getViewState().handleError(error.getMessage()));
    }

    public void getCachedData(){
        DatabaseHelper.getInstance()
                .readJson(TOP_ARTISTS_TAG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        jsonObject -> {
                            if (jsonObject == null) getViewState().updateData();
                            else getViewState().setData(JsonParser
                                    .parse(TopArtists.class, jsonObject)
                                    .getArtists());
                        },
                        error -> getViewState().handleError(error.getMessage()));
    }

}
