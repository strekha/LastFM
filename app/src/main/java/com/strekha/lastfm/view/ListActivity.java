package com.strekha.lastfm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.strekha.lastfm.model.top.Artist;
import com.strekha.lastfm.model.top.TopArtists;
import com.strekha.lastfm.presenter.ListActivityPresenter;
import com.strekha.lastfm.presenter.ListPresenter;

import java.util.List;

public class ListActivity extends AppCompatActivity implements View {

    private ListPresenter presenter = new ListActivityPresenter();
    private RecyclerView recyclerView;
    private TopArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_top_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        presenter.create(this);
    }

    @Override
    public void setData(List<Artist> list) {
        adapter = new TopArtistAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
