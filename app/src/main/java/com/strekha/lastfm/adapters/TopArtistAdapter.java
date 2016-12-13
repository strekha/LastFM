package com.strekha.lastfm.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.strekha.lastfm.R;
import com.strekha.lastfm.model.ViewHolder;
import com.strekha.lastfm.model.top.Artist;

import java.util.List;

public class TopArtistAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Artist> topArtists;

    public TopArtistAdapter(List<Artist> topArtists) {
        this.topArtists = topArtists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder card, int position) {
        card.mName.setText(topArtists.get(position).getName());
        card.mPlaycounts.setText(topArtists.get(position).getPlaycount());
        card.mListeners.setText(topArtists.get(position).getListeners());
        card.mImage.setImageURI(Uri.parse(topArtists.get(position).getImage().get(2).getText()));
    }

    @Override
    public int getItemCount() {
        return topArtists.size();
    }
}
