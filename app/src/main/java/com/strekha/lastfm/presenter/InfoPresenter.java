package com.strekha.lastfm.presenter;

import com.strekha.lastfm.view.InfoView;

public interface InfoPresenter {
    void getData(String title);
    void bindView(InfoView view);
}
