
package com.strekha.lastfm.entity.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    private static final String LISTENERS = "listeners";
    private static final String PLAYCOUNT = "playcount";

    @SerializedName(LISTENERS)
    @Expose
    private String listeners;
    @SerializedName(PLAYCOUNT)
    @Expose
    private String playcount;

    String getListeners() {
        return listeners;
    }

    String getPlaycount() {
        return playcount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        if (listeners != null ? !listeners.equals(stats.listeners) : stats.listeners != null)
            return false;
        return playcount != null ? playcount.equals(stats.playcount) : stats.playcount == null;

    }

    @Override
    public int hashCode() {
        int result = listeners != null ? listeners.hashCode() : 0;
        result = 31 * result + (playcount != null ? playcount.hashCode() : 0);
        return result;
    }
}
