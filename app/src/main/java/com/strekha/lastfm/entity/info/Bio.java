
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bio {
//TODO Think about override equals and hashcode fo all entities this is a best practice.
    @SerializedName("content")
    @Expose
    private String content;

    public String getContent() {
        return content;
    }
}
