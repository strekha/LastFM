package com.strekha.lastfm.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.strekha.lastfm.entity.top.Artist;
import com.strekha.lastfm.R;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private static final int PREVIEW = 2;

    private CardView mCard;
    private SimpleDraweeView mImage;
    private TextView mListeners;
    private TextView mName;
    private TextView mPlaycounts;

    public ArtistViewHolder(CardView itemView) {
        super(itemView);
        mCard = itemView;
        mListeners = (TextView) itemView.findViewById(R.id.listeners_card);
        mPlaycounts = (TextView) itemView.findViewById(R.id.playcount_card);
        mName = (TextView) itemView.findViewById(R.id.artist_name_card);
        mImage = (SimpleDraweeView) itemView.findViewById(R.id.artist_image_card);
    }

    public void setData(Artist artist){
        mName.setText(artist.getName());
        mPlaycounts.setText(artist.getPlaycount());
        mListeners.setText(artist.getListeners());
        mImage.setImageURI(artist.getImages().get(PREVIEW).getUri());
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        mCard.setOnClickListener(onClickListener);
    }

    public String getName(){
        return mName.getText().toString();
    }
}
