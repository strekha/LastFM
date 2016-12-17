package com.strekha.lastfm.view;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.expandableAdapter.ExpandableAdapter;
import com.strekha.lastfm.adapters.expandableAdapter.SimilarGroup;
import com.strekha.lastfm.model.content.info.ArtistInfo;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;
import com.strekha.lastfm.presenter.InfoPresenter;

import java.util.Arrays;

public class ArtistInfoActivity extends AppCompatActivity implements InfoView{

    private InfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_artist_info);
        String artistTitle = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(artistTitle);
        getSupportActionBar().setHomeButtonEnabled(true);

        presenter = new ArtistInfoPresenter();
        presenter.bindView(this);
        presenter.getData(artistTitle, getResources().getString(R.string.lang));
    }

    @Override
    public void setInfo(ArtistInfo artistInfo) {
        ((ContentLoadingProgressBar) findViewById(R.id.info_loading_progress)).hide();
        TextView listeners = (TextView) findViewById(R.id.listeners);
        TextView playcount = (TextView) findViewById(R.id.playcount);
        TextView bio = (TextView) findViewById(R.id.bio);
        SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.artist_cover);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.similar_artists);

        findViewById(R.id.playcount_label).setVisibility(View.VISIBLE);
        findViewById(R.id.listeners_label).setVisibility(View.VISIBLE);

        listeners.setText(artistInfo.getArtist().getStats().getListeners());
        playcount.setText(artistInfo.getArtist().getStats().getPlaycount());
        image.setImageURI(artistInfo.getArtist().getImage().get(3).getText());
        bio.setText(artistInfo.getArtist().getBio().getContent());

        ExpandableAdapter adapter = new ExpandableAdapter(Arrays.asList(
                new SimilarGroup(getResources().getString(R.string.similar),
                        artistInfo.getArtist().getSimilar().getArtist())));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
