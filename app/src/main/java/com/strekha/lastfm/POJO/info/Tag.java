
package com.strekha.lastfm.POJO.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
