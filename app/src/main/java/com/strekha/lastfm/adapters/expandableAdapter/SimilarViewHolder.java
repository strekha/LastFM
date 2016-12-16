package com.strekha.lastfm.adapters.expandableAdapter;

import android.view.View;
import android.widget.TextView;

import com.strekha.lastfm.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;


public class SimilarViewHolder extends GroupViewHolder {

    private TextView title;

    public SimilarViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.similar_title);
    }

    public void setTitle(ExpandableGroup group) {
        title.setText(group.getTitle());
    }
}
