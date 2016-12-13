
package com.strekha.lastfm.model.content.info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Similar {

    @SerializedName("artist")
    @Expose
    private List<Artist_> artist = null;

    /**
     * 
     * @return
     *     The artist
     */
    public List<Artist_> getArtist() {
        return artist;
    }

    /**
     * 
     * @param artist
     *     The artist
     */
    public void setArtist(List<Artist_> artist) {
        this.artist = artist;
    }

}
