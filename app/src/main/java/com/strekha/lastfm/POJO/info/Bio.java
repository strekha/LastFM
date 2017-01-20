
package com.strekha.lastfm.POJO.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bio {

    @SerializedName("content")
    @Expose
    private String content;

    public String getContent() {
        return content;
    }
}
