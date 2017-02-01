
package com.strekha.lastfm.entity.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("#text")
    @Expose
    private String text;

    public String getUri() {
        return text;
    }
}
