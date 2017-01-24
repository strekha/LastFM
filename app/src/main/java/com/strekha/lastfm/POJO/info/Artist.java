
package com.strekha.lastfm.pojo.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.strekha.lastfm.pojo.top.Image;

public class Artist implements Parcelable {

    public static final int LARGE_COVER = 3;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("stats")
    @Expose
    private Stats stats;
    @SerializedName("similar")
    @Expose
    private Similar similar;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("bio")
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
}
