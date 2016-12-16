package com.strekha.lastfm.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.R;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    public CardView mCard;
    public SimpleDraweeView mImage;
    public TextView mListeners;
    public TextView mName;
    public TextView mPlaycounts;

    public ArtistViewHolder(CardView itemView) {
        super(itemView);
        mCard = itemView;
        mListeners = (TextView) itemView.findViewById(R.id.listeners_card);
        mPlaycounts = (TextView) itemView.findViewById(R.id.playcount_card);
        mName = (TextView) itemView.findViewById(R.id.artist_name_card);
        mImage = (SimpleDraweeView) itemView.findViewById(R.id.artist_image_card);
    }
}
