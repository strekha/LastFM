package com.strekha.lastfm.view.interfaces;

import com.strekha.lastfm.entity.info.Artist;


public interface InfoView{

    void setInfo(Artist artist);

    void showErrorMessage(String errorMessage);

    void showProgress();

    void hideProgress();

    void showNetworkIsNotAvailable();
}
