
package com.strekha.lastfm.entity.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArtists {

    private static final String ARTISTS = "artists";

    @SerializedName(ARTISTS)
    @Expose
    private Artists artists;

    public List<Artist> getArtists() {
        return artists.getArtist();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopArtists that = (TopArtists) o;

        return artists != null ? artists.equals(that.artists) : that.artists == null;

    }

    @Override
    public int hashCode() {
        return artists != null ? artists.hashCode() : 0;
    }
}
