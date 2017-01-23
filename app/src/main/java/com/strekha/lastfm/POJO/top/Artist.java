
package com.strekha.lastfm.pojo.top;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;

    public String getName() {
        return name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public List<Image> getImages() {
        return image;
    }
}
