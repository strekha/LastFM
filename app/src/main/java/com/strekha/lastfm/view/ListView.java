package com.strekha.lastfm.view;

import com.strekha.lastfm.model.top.Artist;

import java.util.List;

/**
 * Created by HP on 13.12.2016.
 */

public interface ListView {
    void setData(List<Artist> list);
    void makeToast(String message);
}
