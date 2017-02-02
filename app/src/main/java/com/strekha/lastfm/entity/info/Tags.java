
package com.strekha.lastfm.entity.info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {

    private static final String TAG = "tag";

    @SerializedName(TAG)
    @Expose
    private List<Tag> tag = null;

    List<Tag> getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tags tags = (Tags) o;

        return tag != null ? tag.equals(tags.tag) : tags.tag == null;

    }

    @Override
    public int hashCode() {
        return tag != null ? tag.hashCode() : 0;
    }
}
