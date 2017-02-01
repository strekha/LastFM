package com.strekha.lastfm.adapters.expandableAdapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.strekha.lastfm.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class SimilarViewHolder extends GroupViewHolder {

    private TextView title;
    private ImageView arrow;

    public SimilarViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.similar_title);
        arrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
    }

    public void setTitle(ExpandableGroup group) {
        title.setText(group.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                //TODO Use named constants instead of magic number.
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
