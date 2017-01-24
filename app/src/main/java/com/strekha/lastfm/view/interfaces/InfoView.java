package com.strekha.lastfm.view.interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.strekha.lastfm.pojo.info.Artist;


public interface InfoView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setInfo(Artist artist);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void handleError(String errorMessage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showNetworkIsNotAvailable();
}
