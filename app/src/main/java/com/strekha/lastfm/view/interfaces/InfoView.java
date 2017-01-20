package com.strekha.lastfm.view.interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.strekha.lastfm.POJO.info.ArtistInfo;


public interface InfoView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setInfo(ArtistInfo artist);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void handleError(String errorMessage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateData();
}
