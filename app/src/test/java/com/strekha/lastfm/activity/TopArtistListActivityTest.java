package com.strekha.lastfm.activity;


import com.strekha.lastfm.BuildConfig;
import com.strekha.lastfm.LastFmAppTest;
import com.strekha.lastfm.presenter.TopArtistsListPresenter;
import com.strekha.lastfm.view.ListActivity_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = LastFmAppTest.class, sdk = 23)
public class TopArtistListActivityTest {

    private ListActivity_ mActivity;

    @Mock
    public static TopArtistsListPresenter mPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mActivity = Robolectric.setupActivity(ListActivity_.class);
    }

    @Test
    public void verify_requestData_onCreate(){
        verify(mPresenter).requestData();
    }

}
