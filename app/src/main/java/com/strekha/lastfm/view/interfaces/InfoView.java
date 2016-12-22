package com.strekha.lastfm.view.interfaces;

import com.strekha.lastfm.POJO.info.ArtistInfo;

public interface InfoView {
    void setInfo(ArtistInfo artistInfo);
    boolean isNetworkAvailable();
    void showNetworkIsNotAvailable();
    void makeToast(String message);
}
