package com.strekha.lastfm.presenter;

import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.model.TopArtistsModel;
import com.strekha.lastfm.model.network.NetworkChangeReceiver;
import com.strekha.lastfm.view.interfaces.ListView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@EBean
public class TopArtistsListPresenter {

    @Bean
    TopArtistsModel mModel;
    private ListView mView;
    private Subscription mCachedDataSubscription;
    private Subscription mFreshDataSubscription;

    public void requestData() {
        getViewState().showProgress();
        mCachedDataSubscription = mModel
                .requestCachedData()
                .filter(list -> list != null)
                .switchIfEmpty(mModel.requestFreshData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }


    public void requestFreshData() {
        unsubscribe(mFreshDataSubscription);
        mFreshDataSubscription = mModel
                .requestFreshData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setData, this::handleError);
    }

    private void setData(List<Artist> artists) {
        getViewState().setData(artists);
        getViewState().hideProgress();
    }

    private void handleError(Throwable error) {
        if (!NetworkChangeReceiver.isNetworkAvailable()) {
            getViewState().showNetworkIsNotAvailable();
        }
        else getViewState().handleError(error.getMessage());
    }

    private ListView getViewState() {
        return mView;
    }

    public void setViewState(ListView view) {
        mView = view;
    }

    public void onDestroyView() {
        unsubscribe(mCachedDataSubscription);
        unsubscribe(mFreshDataSubscription);
    }

    private void unsubscribe(Subscription subscription){
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
