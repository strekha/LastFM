package com.strekha.lastfm.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.strekha.lastfm.R;
import com.strekha.lastfm.POJO.top.Artist;

import java.util.ArrayList;
import java.util.List;

public class TopArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

    private List<Artist> mTopArtists = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String artist);
    }

    public void setList(List<Artist> topArtists){
        this.mTopArtists = topArtists;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ArtistViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder viewHolder, int position) {

        viewHolder.setOnClickListener(view -> mOnItemClickListener.onItemClick(viewHolder.getName()));
        viewHolder.setData(mTopArtists.get(position));
    }

    @Override
    public int getItemCount() {
        return mTopArtists.size();
    }
}
