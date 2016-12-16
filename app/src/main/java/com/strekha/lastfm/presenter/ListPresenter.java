package com.strekha.lastfm.presenter;

import com.strekha.lastfm.view.InfoView;
import com.strekha.lastfm.view.ListView;

public interface ListPresenter {

    void getData();
    void bindView(ListView view);

}
