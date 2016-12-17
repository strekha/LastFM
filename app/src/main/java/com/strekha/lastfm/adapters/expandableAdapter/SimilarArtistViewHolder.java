package com.strekha.lastfm.adapters.expandableAdapter;

import android.view.View;
import android.widget.TextView;

import com.strekha.lastfm.R;
import com.strekha.lastfm.model.content.info.Artist_;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class SimilarArtistViewHolder extends ChildViewHolder {

    private TextView title;

    public SimilarArtistViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.similar_artists_child);
    }

    public void onBind(Artist_ artist) {
        title.setText(artist.getName());
    }


}
