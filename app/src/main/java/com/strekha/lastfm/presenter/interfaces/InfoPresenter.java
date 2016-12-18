package com.strekha.lastfm.presenter.interfaces;

import com.strekha.lastfm.view.interfaces.InfoView;

public interface InfoPresenter {
    void getData(String title, String lang);
    void bindView(InfoView view);
}
