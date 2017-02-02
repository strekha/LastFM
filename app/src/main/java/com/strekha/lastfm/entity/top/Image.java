
package com.strekha.lastfm.entity.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    public static final String TEXT = "#text";
    @SerializedName(TEXT)
    @Expose
    private String text;

    public String getUri() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return text != null ? text.equals(image.text) : image.text == null;

    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
