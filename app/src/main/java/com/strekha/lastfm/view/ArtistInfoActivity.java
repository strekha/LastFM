package com.strekha.lastfm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.LastFmApplication;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.expandableAdapter.ExpandableAdapter;
import com.strekha.lastfm.adapters.expandableAdapter.SimilarGroup;
import com.strekha.lastfm.pojo.info.Artist;
import com.strekha.lastfm.pojo.info.Tag;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;
import com.strekha.lastfm.view.interfaces.InfoView;

import java.util.Collections;

public class ArtistInfoActivity extends MvpAppCompatActivity implements InfoView {

    public static final int COVER = 3;
    private static String LANG;

    @InjectPresenter
    public ArtistInfoPresenter mPresenter;
    private GridLayout mTagsLayout;
    private String mArtistTitle;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        mArtistTitle = getIntent().getStringExtra(ListActivity.ARTIST_TITLE);
        LANG = getString(R.string.lang);

        getSupportActionBar().setTitle(mArtistTitle);
        getSupportActionBar().setHomeButtonEnabled(true);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestFreshData(mArtistTitle, LANG));

        mPresenter.requestFreshData(mArtistTitle, LANG);
    }



    @Override
    public void setInfo(Artist artistInfo) {

        mTagsLayout = (GridLayout) findViewById(R.id.tags);

        TextView listeners = (TextView) findViewById(R.id.listeners);
        TextView playcount = (TextView) findViewById(R.id.playcount);
        TextView bio = (TextView) findViewById(R.id.bio);
        SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.artist_cover);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.similar_artists);

        findViewById(R.id.playcount_label).setVisibility(View.VISIBLE);
        findViewById(R.id.listeners_label).setVisibility(View.VISIBLE);

        listeners.setText(artistInfo.getListeners());
        playcount.setText(artistInfo.getPlaycount());
        image.setImageURI(artistInfo.getCoverUri());
        image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());

        String biography = artistInfo.getFullBio();
        biography = biography.substring(0, biography.lastIndexOf("<a href"));
        bio.setText(biography);

        mTagsLayout.removeAllViews();
        for (Tag tag : artistInfo.getTags()){
            addTag(tag.getName());
        }

        ExpandableAdapter adapter = new ExpandableAdapter(Collections.singletonList(
                new SimilarGroup(getResources().getString(R.string.similar),
                        artistInfo.getSimilar())));

        adapter.setOnItemClickListener(this::startInfoActivity);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void handleError(String errorMessage) {
        LastFmApplication.getInstance().makeToast(errorMessage);
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
        LastFmApplication.getInstance().makeToast(getString(R.string.network_is_not_available));
    }

    private void startInfoActivity(String artist) {
        Intent intent = new Intent(this, ArtistInfoActivity.class);
        intent.putExtra(ListActivity.ARTIST_TITLE, artist);
        startActivity(intent);
    }

    private void addTag(String tag){
        TextView newTag = new TextView(this);
        newTag.setText(tag);
        mTagsLayout.addView(newTag, getParamsForTag(newTag));
    }

    private GridLayout.LayoutParams getParamsForTag(TextView textView){
        textView.setBackground(getResources().getDrawable(R.drawable.tag_background));
        textView.setPadding((int) getResources().getDimension(R.dimen.horizontal_tag_padding),
                (int) getResources().getDimension(R.dimen.vertical_tag_padding),
                (int) getResources().getDimension(R.dimen.horizontal_tag_padding),
                (int) getResources().getDimension(R.dimen.vertical_tag_padding));
        textView.setTextColor(getResources().getColor(R.color.white));
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.setMargins(0, (int) getResources().getDimension(R.dimen.vertical_tag_margin),
                (int) getResources().getDimension(R.dimen.horizontal_tag_margin), 0);
        textView.setLayoutParams(params);
        return params;
    }

}
