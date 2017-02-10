package com.strekha.lastfm.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.strekha.lastfm.R;
import com.strekha.lastfm.UiUtils;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.presenter.TopArtistsListPresenter;
import com.strekha.lastfm.view.interfaces.ListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_top_list)
public class ListActivity extends AppCompatActivity implements ListView {

    public static final String ARTIST_TITLE = "title";

    @Bean                           TopArtistsListPresenter mPresenter;
    @Bean                           TopArtistAdapter mAdapter;
    @ViewById(R.id.swipe_refresh)   SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewById(R.id.recycleView)     RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

    }

    @AfterInject
    void afterInject(){
        mPresenter.setViewState(this);
    }

    @AfterViews
    void afterViews(){
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestFreshData());
        mAdapter.setOnItemClickListener(
                artist -> ArtistInfoActivity_.intent(this).extra(ARTIST_TITLE, artist).start());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.requestData();
    }

    @Override
    public void setData(List<Artist> list) {
        mAdapter.setList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleError(String errorMessage) {
        UiUtils.showPopup(errorMessage);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNetworkIsNotAvailable() {
        hideProgress();
        UiUtils.showPopup(getString(R.string.network_is_not_available));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyView();
    }
}