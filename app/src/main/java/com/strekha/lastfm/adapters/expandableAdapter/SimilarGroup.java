package com.strekha.lastfm.adapters.expandableAdapter;

import com.strekha.lastfm.POJO.info.Artist_;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class SimilarGroup extends ExpandableGroup<Artist_> {

    public SimilarGroup(String title, List<Artist_> items) {
        super(title, items);
    }
}
