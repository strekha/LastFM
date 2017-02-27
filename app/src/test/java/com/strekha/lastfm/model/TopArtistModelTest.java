package com.strekha.lastfm.model;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.entity.top.TopArtists;
import com.strekha.lastfm.model.db.DatabaseHelper;
import com.strekha.lastfm.model.deserialization.JsonParser;
import com.strekha.lastfm.model.network.LastFM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;
import rx.observers.TestSubscriber;

@RunWith(PowerMockRunner.class)
@PrepareForTest(JsonParser.class)
public class TopArtistModelTest {

    @Mock        DatabaseHelper mDatabase;
    @Mock        LastFM mServiceApi;
    @Mock        TopArtists mArtists;

    @InjectMocks TopArtistsModel mModel;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(JsonParser.class);
        when(mArtists.getArtists()).thenReturn(Collections.EMPTY_LIST);
        when(JsonParser.parse(anyObject(), anyString())).thenReturn(mArtists);
    }

    @Test
    public void should_writeJson_when_RequestFromApi() {
        String json = "some json here";
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();

        when(mServiceApi.getTopArtists()).thenReturn(Observable.just(json));

        mModel.requestFreshData().subscribe(testSubscriber);

        verify(mDatabase).writeJson(TopArtistsModel.TOP_ARTISTS_TAG, json);
        testSubscriber.assertValue(Collections.EMPTY_LIST);
        testSubscriber.assertCompleted();
    }

    @Test
    public void should_receiveNull_when_CacheIsEmpty(){
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();

        when(mDatabase.readJson(anyString())).thenReturn(null);
        mModel.requestCachedData().subscribe(testSubscriber);
        testSubscriber.assertValue(null);
        testSubscriber.assertCompleted();
    }

    @Test
    public void should_receiveSomeValue_when_CacheIsNotEmpty(){
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        when(mDatabase.readJson(anyString())).thenReturn(anyString());
        mModel.requestCachedData().subscribe(testSubscriber);
        testSubscriber.assertValue(Collections.EMPTY_LIST);
        testSubscriber.assertCompleted();
    }

    @Test
    public void should_receiveException_when_apiEmitError(){
        String errorMessage = "some error here";
        TestSubscriber<List<Artist>> testSubscriber = new TestSubscriber<>();
        Throwable error = new Throwable(errorMessage);
        when(mServiceApi.getTopArtists()).thenReturn(Observable.error(error));
        mModel.requestFreshData().subscribe(testSubscriber);
        testSubscriber.assertError(error);
    }
}
