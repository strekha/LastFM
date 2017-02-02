
package com.strekha.lastfm.entity.top;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists {

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

        Artists artists = (Artists) o;

        return artist != null ? artist.equals(artists.artist) : artists.artist == null;

    }

    @Override
    public int hashCode() {
        return artist != null ? artist.hashCode() : 0;
    }
}
