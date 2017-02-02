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

    private static final int DURATION_MILLIS = 300;
    private static final int FROM_DEGREES = 360;
    private static final int TO_DEGREES = 180;
    private static final float PIVOT_VALUE = 0.5f;
    private TextView mTitle;
    private ImageView mArrow;

    SimilarViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.similar_title);
        mArrow = (ImageView) itemView.findViewById(R.id.list_item_genre_arrow);
    }

    void setTitle(ExpandableGroup group) {
        mTitle.setText(group.getTitle());
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
                new RotateAnimation(FROM_DEGREES, TO_DEGREES, RELATIVE_TO_SELF, PIVOT_VALUE, RELATIVE_TO_SELF, PIVOT_VALUE);
        rotate.setDuration(DURATION_MILLIS);
        rotate.setFillAfter(true);
        mArrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(TO_DEGREES, FROM_DEGREES, RELATIVE_TO_SELF, PIVOT_VALUE, RELATIVE_TO_SELF, PIVOT_VALUE);
        rotate.setDuration(DURATION_MILLIS);
        rotate.setFillAfter(true);
        mArrow.setAnimation(rotate);
    }
}
