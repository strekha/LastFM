
package com.strekha.lastfm.entity.top;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist = null;

    public List<Artist> getArtist() {
        return artist;
    }
}
