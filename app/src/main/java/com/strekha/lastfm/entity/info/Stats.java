
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("playcount")
    @Expose
    private String playcount;

    public String getListeners() {
        return listeners;
    }

    public String getPlaycount() {
        return playcount;
    }
}
