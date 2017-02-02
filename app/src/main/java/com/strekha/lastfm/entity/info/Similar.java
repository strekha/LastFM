
package com.strekha.lastfm.entity.info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    private static final String ARTIST = "artist";

    @SerializedName(ARTIST)
    @Expose
    private List<Artist> artist = null;

    public List<Artist> getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Similar similar = (Similar) o;

        return artist != null ? artist.equals(similar.artist) : similar.artist == null;

    }

    @Override
    public int hashCode() {
        return artist != null ? artist.hashCode() : 0;
    }
}
