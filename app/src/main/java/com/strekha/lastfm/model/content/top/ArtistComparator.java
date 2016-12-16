package com.strekha.lastfm.model.content.top;

import java.util.Comparator;

public class ArtistComparator implements Comparator<Artist> {
    @Override
    public int compare(Artist o1, Artist o2) {
        return Integer.parseInt(o2.getPlaycount()) - Integer.parseInt(o1.getPlaycount());
    }
}
