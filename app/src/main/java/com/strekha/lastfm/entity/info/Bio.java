
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bio {

    private static final String CONTENT = "content";

    @SerializedName(CONTENT)
    @Expose
    private String content;

    String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bio bio = (Bio) o;

        return content != null ? content.equals(bio.content) : bio.content == null;

    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
