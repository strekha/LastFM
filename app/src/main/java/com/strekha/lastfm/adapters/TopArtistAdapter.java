package com.strekha.lastfm.adapters;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.strekha.lastfm.R;
import com.strekha.lastfm.POJO.top.Artist;

import java.util.List;

public class TopArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

    private List<Artist> topArtists;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String artist);
    }

    public void setList(List<Artist> topArtists) {
        this.topArtists = topArtists;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ArtistViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder card, int position) {
        card.mCard.setOnClickListener(v -> onItemClickListener.onItemClick(card.mName.getText().toString()));
        card.mName.setText(topArtists.get(position).getName());
        card.mPlaycounts.setText(topArtists.get(position).getPlaycount());
        card.mListeners.setText(topArtists.get(position).getListeners());
        card.mImage.setImageURI(Uri.parse(topArtists.get(position).getImage().get(2).getText()));
    }

    @Override
    public int getItemCount() {
        if (topArtists != null) return topArtists.size();
        else return 0;
    }
}
