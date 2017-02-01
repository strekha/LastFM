
package com.strekha.lastfm.pojo.info;

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
