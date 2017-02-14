package com.strekha.lastfm.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.UiUtils;
import com.strekha.lastfm.adapters.expandableAdapter.ExpandableAdapter;
import com.strekha.lastfm.adapters.expandableAdapter.SimilarGroup;
import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.entity.info.Tag;
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
    @ViewById(R.id.tags)                GridLayout mTagsLayout;
    @ViewById(R.id.swipe_refresh)       SwipeRefreshLayout mSwipeRefreshLayout;
    @Extra(ListActivity.ARTIST_TITLE)   String mArtistTitle;


    @AfterInject
    void afterInject(){
        mPresenter.setViewState(this);
    }

    @AfterExtras
    void afterExtras(){
        getSupportActionBar().setTitle(mArtistTitle);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @AfterViews
    void afterViews(){
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.requestFreshData(mArtistTitle));
        mPresenter.requestData(mArtistTitle);
    }

    @Override
    public void setInfo(Artist artistInfo) {

        //TODO Why do you create this variables every time when you set content? Maybe it would be better to use @ViewById for this views too?
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

        //TODO Why instance of adapter created every time? Maybe setter?
        ExpandableAdapter adapter = new ExpandableAdapter(Collections.singletonList(
                new SimilarGroup(getResources().getString(R.string.similar),
                        artistInfo.getSimilar())));

        adapter.setOnItemClickListener(artist -> ArtistInfoActivity_
                .intent(this)
                .extra(ListActivity.ARTIST_TITLE, artist)
                .start());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    //TODO Are all errors handled only this way? If no, than view only can provide intefrace with methods such showPopup, showDialog... and presenter decide what use by himself
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


    private void addTag(String tag){
        TextView newTag = new TextView(this);
        newTag.setText(tag);
        mTagsLayout.addView(newTag, getParamsForTag(newTag));
    }

    private GridLayout.LayoutParams getParamsForTag(TextView textView){
        textView.setBackground(getResources().getDrawable(R.drawable.tag_background));
        textView.setPadding(UiUtils.getDimension(R.dimen.horizontal_tag_padding),
                UiUtils.getDimension(R.dimen.vertical_tag_padding),
                UiUtils.getDimension(R.dimen.horizontal_tag_padding),
                UiUtils.getDimension(R.dimen.vertical_tag_padding));
        textView.setTextColor(UiUtils.getColor(R.color.white));
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.setMargins(0, UiUtils.getDimension(R.dimen.vertical_tag_margin),
                UiUtils.getDimension(R.dimen.horizontal_tag_margin), 0);
        textView.setLayoutParams(params);
        return params;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroyView();
    }
}
