
package com.strekha.lastfm.entity.top;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    private static final String NAME = "name";
    private static final String PLAYCOUNT = "playcount";
    private static final String LISTENERS = "listeners";
    private static final String IMAGE = "image";

    @SerializedName(NAME)
    @Expose
    private String name;
    @SerializedName(PLAYCOUNT)
    @Expose
    private String playcount;
    @SerializedName(LISTENERS)
    @Expose
    private String listeners;
    @SerializedName(IMAGE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (!name.equals(artist.name)) return false;
        if (playcount != null ? !playcount.equals(artist.playcount) : artist.playcount != null)
            return false;
        return listeners != null ? listeners.equals(artist.listeners) : artist.listeners == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (playcount != null ? playcount.hashCode() : 0);
        result = 31 * result + (listeners != null ? listeners.hashCode() : 0);
        return result;
    }
}
