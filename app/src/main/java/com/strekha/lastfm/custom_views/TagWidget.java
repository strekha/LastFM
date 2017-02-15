package com.strekha.lastfm.custom_views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;

import com.strekha.lastfm.R;
import com.strekha.lastfm.UiUtils;
import com.strekha.lastfm.entity.info.Tag;

import java.util.List;

public class TagWidget extends GridLayout {

    public TagWidget(Context context) {
        super(context);
    }

    public TagWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TagWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setTags(List<Tag> tags){
        removeAllViews();
        for (Tag tag : tags){
            addTag(tag.getName());
        }
    }

    private void addTag(String tag){
        TextView newTag = new TextView(getContext());
        newTag.setText(tag);
        addView(newTag, getParamsForTag(newTag));
    }

    private GridLayout.LayoutParams getParamsForTag(TextView textView){
        textView.setBackground(getResources().getDrawable(R.drawable.tag_background));
        textView.setPadding(UiUtils.getDimension(R.dimen.horizontal_tag_padding),
                UiUtils.getDimension(R.dimen.vertical_tag_padding),
                UiUtils.getDimension(R.dimen.horizontal_tag_padding),
                UiUtils.getDimension(R.dimen.vertical_tag_padding));
        textView.setTextColor(UiUtils.getColorStateList(R.color.tag_view_colors));
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams();
        params.setMargins(0, UiUtils.getDimension(R.dimen.vertical_tag_margin),
                UiUtils.getDimension(R.dimen.horizontal_tag_margin), 0);
        textView.setLayoutParams(params);
        return params;
    }
}
