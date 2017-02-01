
package com.strekha.lastfm.pojo.top;

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
