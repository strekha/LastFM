
package com.strekha.lastfm.entity.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.strekha.lastfm.entity.top.Image;

public class Artist implements Parcelable {

    private static final int LARGE_COVER = 3;
    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String STATS = "stats";
    private static final String SIMILAR = "similar";
    private static final String TAGS = "tags";
    private static final String BIO = "bio";

    @SerializedName(NAME)
    @Expose
    private String name;
    @SerializedName(IMAGE)
    @Expose
    private List<Image> image = null;
    @SerializedName(STATS)
    @Expose
    private Stats stats;
    @SerializedName(SIMILAR)
    @Expose
    private Similar similar;
    @SerializedName(TAGS)
    @Expose
    private Tags tags;
    @SerializedName(BIO)
    @Expose
    private Bio bio;

    protected Artist(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getCoverUri(){
        return image.get(LARGE_COVER).getUri();
    }

    public String getListeners(){
        return stats.getListeners();
    }

    public String getPlaycount(){
        return stats.getPlaycount();
    }

    public List<Artist> getSimilar() {
        return similar.getArtist();
    }

    public List<Tag> getTags() {
        return tags.getTag();
    }

    public String getFullBio() {
        return bio.getContent();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;
        if (stats != null ? !stats.equals(artist.stats) : artist.stats != null) return false;
        return bio != null ? bio.equals(artist.bio) : artist.bio == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        return result;
    }
}
