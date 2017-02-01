
package com.strekha.lastfm.pojo.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArtists {

    @SerializedName("artists")
    @Expose
    private Artists artists;

    public List<Artist> getArtists() {
        return artists.getArtist();
    }
}
