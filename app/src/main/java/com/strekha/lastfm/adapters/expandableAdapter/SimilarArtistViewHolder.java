package com.strekha.lastfm.adapters.expandableAdapter;

import android.view.View;
import android.widget.TextView;

import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class SimilarArtistViewHolder extends ChildViewHolder {

    private TextView mTitle;
    private View mView;

    private TopArtistAdapter.OnItemClickListener onItemClickListener;

    SimilarArtistViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mTitle = (TextView) itemView.findViewById(R.id.similar_artists_child);
    }

    void onBind(Artist artist) {
        mTitle.setText(artist.getName());
        mView.setOnClickListener(v -> onItemClickListener.onItemClick(artist.getName()));
    }

    void setOnItemClickListener(TopArtistAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
