package com.strekha.lastfm.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.UiUtils;
import com.strekha.lastfm.adapters.expandableAdapter.ExpandableAdapter;
import com.strekha.lastfm.adapters.expandableAdapter.SimilarGroup;
import com.strekha.lastfm.custom_views.TagWidget;
import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;
import com.strekha.lastfm.view.interfaces.InfoView;

import org.androidannotations.annotations.AfterExtras;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;

@EActivity(R.layout.activity_artist_info)
public class ArtistInfoActivity extends AppCompatActivity implements InfoView {

    @Bean                               ArtistInfoPresenter mPresenter;
    @ViewById(R.id.tags)                TagWidget mTagsLayout;
    @ViewById(R.id.swipe_refresh)       SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewById(R.id.listeners)           TextView mListeners;
    @ViewById(R.id.playcount)           TextView mPlaycount;
    @ViewById(R.id.bio)                 TextView mBio;
    @ViewById(R.id.artist_cover)        SimpleDraweeView mCover;
    @ViewById(R.id.similar_artists)     RecyclerView mSimilarArtists;
    @Extra(ListActivity.ARTIST_TITLE)   String mArtistTitle;


    @AfterInject
    void afterInject(){
        mPresenter.setViewState(this);
    }

    @AfterExtras
    void afterExtras(){
        getSupportActionBar().setTitle(mArtistTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @AfterViews
    void afterViews(){
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestFreshData(mArtistTitle));
        mPresenter.requestData(mArtistTitle);
    }

    @Override
    public void setInfo(Artist artistInfo) {

        findViewById(R.id.playcount_label).setVisibility(View.VISIBLE);
        findViewById(R.id.listeners_label).setVisibility(View.VISIBLE);

        mListeners.setText(artistInfo.getListeners());
        mPlaycount.setText(artistInfo.getPlaycount());
        mCover.setImageURI(artistInfo.getCoverUri());
        mCover.getHierarchy().setProgressBarImage(new ProgressBarDrawable());

        String biography = artistInfo.getFullBio();
        biography = biography.substring(0, biography.lastIndexOf("<a href"));
        mBio.setText(biography);

        mTagsLayout.setTags(artistInfo.getTags());

        //TODO Why instance of adapter created every time? Maybe setter?
        //because ExpandableAdapter superclass has not constructor without parameters.
        //and I cant inject adapter when activity created.
        ExpandableAdapter adapter = new ExpandableAdapter(Collections.singletonList(
                new SimilarGroup(getResources().getString(R.string.similar),
                        artistInfo.getSimilar())));

        adapter.setOnItemClickListener(artist -> ArtistInfoActivity_
                .intent(this)
                .extra(ListActivity.ARTIST_TITLE, artist)
                .start());

        mSimilarArtists.setLayoutManager(new LinearLayoutManager(this));
        mSimilarArtists.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
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
