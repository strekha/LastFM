package com.strekha.lastfm.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.strekha.lastfm.POJO.info.ArtistInfo;
import com.strekha.lastfm.model.DatabaseHelper;
import com.strekha.lastfm.model.JsonParser;
import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.view.interfaces.InfoView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ArtistInfoPresenter extends MvpPresenter<InfoView> {

    private LastFM mLastFM = LastFM.getInstance();

    public void getFreshData(String artist, String lang) {
        getViewState().showProgress();
        mLastFM.getArtistInfo(artist, lang)
                .subscribeOn(Schedulers.io())
                .retry(1)
                .observeOn(Schedulers.computation())
                .doOnNext(json -> DatabaseHelper.getInstance().writeJson(artist, json))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        json -> {
                            getViewState().setInfo(JsonParser
                                    .parse(ArtistInfo.class, json));
                            getViewState().hideProgress();
                        },
                        error -> getViewState().handleError(error.getMessage()));
    }

    public void getCachedData(String artist){
        DatabaseHelper.getInstance()
                .readJson(artist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        jsonObject -> {
                            if (jsonObject == null) {
                                getViewState().updateData();
                            }
                            else {
                                ArtistInfo info = JsonParser.parse(ArtistInfo.class, jsonObject);
                                getViewState().setInfo(info);
                            }
                        },
                        error -> getViewState().handleError(error.getMessage()));
    }
}
