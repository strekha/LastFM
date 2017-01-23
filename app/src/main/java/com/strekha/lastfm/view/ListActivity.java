package com.strekha.lastfm.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.strekha.lastfm.pojo.top.Artist;
import com.strekha.lastfm.presenter.TopArtistsListPresenter;
import com.strekha.lastfm.view.interfaces.ListView;

import java.util.List;

public class ListActivity extends MvpAppCompatActivity implements ListView {

    public static final String ARTIST_TITLE = "title";

    @InjectPresenter
    public TopArtistsListPresenter mPresenter;
    private TopArtistAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestFreshData());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mAdapter = new TopArtistAdapter();
        mAdapter.setOnItemClickListener(this::startInfoActivity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mPresenter.requestData();
    }

    private void startInfoActivity(String artist) {
        Intent intent = new Intent(this, ArtistInfoActivity.class);
        intent.putExtra(ARTIST_TITLE, artist);
        startActivity(intent);
    }

    @Override
    public void setData(List<Artist> list) {
        mAdapter.setList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleError(String errorMessage) {
        makeToast(errorMessage);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkIsNotAvailable() {
        hideProgress();
        makeToast(getString(R.string.network_is_not_available));
    }
}