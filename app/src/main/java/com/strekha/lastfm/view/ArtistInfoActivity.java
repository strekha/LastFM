package com.strekha.lastfm.view;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;
import com.strekha.lastfm.model.LastFM;
import com.strekha.lastfm.model.LastFMApi;
import com.strekha.lastfm.model.content.info.Artist;
import com.strekha.lastfm.model.content.info.ArtistInfo;
import com.strekha.lastfm.presenter.ArtistInfoPresenter;
import com.strekha.lastfm.presenter.InfoPresenter;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArtistInfoActivity extends AppCompatActivity implements InfoView{

    private InfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_artist_info);
        String artistTitle = getIntent().getStringExtra("title");

        presenter = new ArtistInfoPresenter();
        presenter.bindView(this);
    }

    @Override
    public void setInfo(ArtistInfo artistInfo) {

    }
}
