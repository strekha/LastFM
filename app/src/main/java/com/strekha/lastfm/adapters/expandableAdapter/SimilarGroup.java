package com.strekha.lastfm.adapters.expandableAdapter;

import com.strekha.lastfm.entity.info.Artist;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class SimilarGroup extends ExpandableGroup<Artist> {

    public SimilarGroup(String title, List<Artist> items) {
        super(title, items);
    }
}
