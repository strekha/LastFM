
package com.strekha.lastfm.entity.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArtists {
//TODO Use named constants instead of hardcoded string check all files.
    @SerializedName("artists")
    @Expose
    private Artists artists;

    public List<Artist> getArtists() {
        return artists.getArtist();
    }
}
