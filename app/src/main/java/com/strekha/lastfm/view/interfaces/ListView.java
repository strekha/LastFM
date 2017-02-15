package com.strekha.lastfm.view.interfaces;

import com.strekha.lastfm.entity.top.Artist;

import java.util.List;

public interface ListView {

    void setData(List<Artist> list);

    void handleError(String errorMessage);

    void showNetworkIsNotAvailable();

    void showProgress();

    void hideProgress();
}
