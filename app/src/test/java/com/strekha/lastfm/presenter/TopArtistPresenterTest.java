package com.strekha.lastfm.presenter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.strekha.lastfm.BuildConfig;
import com.strekha.lastfm.LastFmAppTest;
import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.utils.RxSchedulersOverrideRule;
import com.strekha.lastfm.view.interfaces.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = LastFmAppTest.class)
public class TopArtistPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mRule = new RxSchedulersOverrideRule();

    @Mock TopArtistsModel mModel;
    @Mock ListView mView;

    private TopArtistsListPresenter mPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new TopArtistsListPresenter(mModel);
        mPresenter.setViewState(mView);

        List<Artist> artists = new ArrayList<>(1);
        artists.add(new Artist());
        when(mModel.requestFreshData()).thenReturn(Observable.just(artists));
    }

    @Test
    public void should_requestCachedData_when_requestData() {
        when(mModel.requestCachedData()).thenReturn(Observable.empty());
        mPresenter.requestData();
        verify(mModel).requestCachedData();
    }

    @Test
    public void should_requestFreshData_when_CacheIsEmpty() {
        when(mModel.requestCachedData()).thenReturn(Observable.just(null));
        mPresenter.requestData();
        verify(mModel).requestFreshData();
    }

    @Test
    public void should_requestFreshData_when_viewRequestFreshData() {
        mPresenter.requestFreshData();
        verify(mModel).requestFreshData();
    }

    @Test
    public void should_setData_when_RequestData() {
        when(mModel.requestCachedData()).thenReturn(Observable.empty());

        mPresenter.requestData();

        verify(mView).showProgress();
        verify(mView).setData(any());
        verify(mView).hideProgress();
    }

    @Test
    public void should_showErrorMessage_when_modelThrowException() {
        String errorMessage = "some error here";
        when(mModel.requestFreshData()).thenReturn(Observable.error(new Throwable(errorMessage)));
        mPresenter.requestFreshData();
        verify(mView).handleError(errorMessage);
    }
}