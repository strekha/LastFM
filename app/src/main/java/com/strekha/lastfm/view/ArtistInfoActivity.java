package com.strekha.lastfm.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.expandableAdapter.ExpandableAdapter;
import com.strekha.lastfm.adapters.expandableAdapter.SimilarGroup;
import com.strekha.lastfm.model.content.info.ArtistInfo;
import com.strekha.lastfm.model.content.info.Tag;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;
import com.strekha.lastfm.presenter.InfoPresenter;

import java.util.Arrays;

public class ArtistInfoActivity extends AppCompatActivity implements InfoView{

    private InfoPresenter presenter;
    private GridLayout tags;

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
        tags = (GridLayout) findViewById(R.id.tags);

        TextView listeners = (TextView) findViewById(R.id.listeners);
        TextView playcount = (TextView) findViewById(R.id.playcount);
        TextView bio = (TextView) findViewById(R.id.bio);
        SimpleDraweeView image = (SimpleDraweeView) findViewById(R.id.artist_cover);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.similar_artists);

        findViewById(R.id.playcount_label).setVisibility(View.VISIBLE);
        findViewById(R.id.listeners_label).setVisibility(View.VISIBLE);

        listeners.setText(artistInfo.getArtist().getStats().getListeners());
        playcount.setText(artistInfo.getArtist().getStats().getPlaycount());
        image.setImageURI(artistInfo.getArtist().getImage().get(4).getText());
        image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
        String biography = artistInfo.getArtist().getBio().getContent();
        biography = biography.substring(0, biography.lastIndexOf("<a href"));
        bio.setText(biography);
        for (Tag tag : artistInfo.getArtist().getTags().getTag()){
            addTag(tag.getName());
        }

        ExpandableAdapter adapter = new ExpandableAdapter(Arrays.asList(
                new SimilarGroup(getResources().getString(R.string.similar),
                        artistInfo.getArtist().getSimilar().getArtist())));

        adapter.setOnItemClickListener(title -> startInfoActivity(title));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void startInfoActivity(String artist) {
        Intent intent = new Intent(this, ArtistInfoActivity.class);
        intent.putExtra("title", artist);
        startActivity(intent);
    }

    private void addTag(String tag){
        TextView newTag = new TextView(this);
        newTag.setBackground(getResources().getDrawable(R.drawable.tag_background));
        newTag.setPadding((int) getResources().getDimension(R.dimen.horizontal_tag_padding),
                (int) getResources().getDimension(R.dimen.vertical_tag_padding),
                (int) getResources().getDimension(R.dimen.horizontal_tag_padding),
                (int) getResources().getDimension(R.dimen.vertical_tag_padding));
        newTag.setTextColor(getResources().getColor(R.color.white));
        newTag.setText(tag);
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.setMargins(0, (int) getResources().getDimension(R.dimen.vertical_tag_margin),
                (int) getResources().getDimension(R.dimen.horizontal_tag_margin), 0);
        newTag.setLayoutParams(params);
        tags.addView(newTag, params);
    }
}
