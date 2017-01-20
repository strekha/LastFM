package com.strekha.lastfm.view.interfaces;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.strekha.lastfm.POJO.top.Artist;

import java.util.List;

public interface ListView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setData(List<Artist> list);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void handleError(String errorMessage);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateData();
}
