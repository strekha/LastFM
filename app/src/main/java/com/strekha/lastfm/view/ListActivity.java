package com.strekha.lastfm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.strekha.lastfm.model.top.Artist;
import com.strekha.lastfm.presenter.ListActivityPresenter;
import com.strekha.lastfm.presenter.ListPresenter;

import java.util.List;

public class ListActivity extends AppCompatActivity implements View {

    private ListPresenter presenter = new ListActivityPresenter(this);
    private TopArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_top_list);
        Log.d("myLog", "asdf");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TopArtistAdapter();
        adapter.setOnItemClickListener(artist -> Log.d("myLog", artist));

        presenter.getData();
    }

    @Override
    public void setData(List<Artist> list) {
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
