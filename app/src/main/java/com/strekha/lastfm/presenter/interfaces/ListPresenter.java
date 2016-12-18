package com.strekha.lastfm.presenter.interfaces;

import com.strekha.lastfm.view.interfaces.ListView;

public interface ListPresenter {

    void getData();
    void bindView(ListView view);

}
