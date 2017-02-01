
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfo {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }


}
