
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfo {

    private static final String ARTIST = "artist";

    @SerializedName(ARTIST)
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistInfo that = (ArtistInfo) o;

        return artist != null ? artist.equals(that.artist) : that.artist == null;

    }

    @Override
    public int hashCode() {
        return artist != null ? artist.hashCode() : 0;
    }
}
