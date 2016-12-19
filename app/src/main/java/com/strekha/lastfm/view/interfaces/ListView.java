package com.strekha.lastfm.view.interfaces;

import com.strekha.lastfm.POJO.top.Artist;

import java.util.List;

/**
 * Created by HP on 13.12.2016.
 */

public interface ListView {
    void setData(List<Artist> list);
    void makeToast(String message);
    boolean isNetworkAvailable();
    void showNetworkIsNotAvailable();
}
