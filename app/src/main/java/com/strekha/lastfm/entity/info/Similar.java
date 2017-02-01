
package com.strekha.lastfm.entity.info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    private List<Artist> artist = null;

    public List<Artist> getArtist() {
        return artist;
    }


}
