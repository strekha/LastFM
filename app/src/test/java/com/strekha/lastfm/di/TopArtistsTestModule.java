package com.strekha.lastfm.di;


import com.strekha.lastfm.activity.TopArtistListActivityTest;
import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.presenter.TopArtistsListPresenter;

/**
 * This module provide moked Presenter instead TopArtistsListPresenter with injected model.
 */
public class TopArtistsTestModule extends TopArtistsModule {

    @Override
    TopArtistsListPresenter providePresenter(TopArtistsModel model) {
        return TopArtistListActivityTest.mPresenter;
    }
}
