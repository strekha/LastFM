package com.strekha.lastfm.adapters.expandableAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strekha.lastfm.entity.info.Artist;
import com.strekha.lastfm.R;
import com.strekha.lastfm.adapters.TopArtistAdapter;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandableAdapter extends
        ExpandableRecyclerViewAdapter<SimilarViewHolder, SimilarArtistViewHolder> {

    private TopArtistAdapter.OnItemClickListener mOnItemClickListener;

    public ExpandableAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public SimilarViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_group_item, parent, false);
        return new SimilarViewHolder(view);
    }

    @Override
    public SimilarArtistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_child_item, parent, false);
        return new SimilarArtistViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SimilarArtistViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {
        final Artist artist = (Artist) group.getItems().get(childIndex);
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.onBind(artist);
    }

    @Override
    public void onBindGroupViewHolder(SimilarViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setTitle(group);
    }

    public void setOnItemClickListener(TopArtistAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
