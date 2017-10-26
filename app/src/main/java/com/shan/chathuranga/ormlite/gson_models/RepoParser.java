
package com.shan.chathuranga.ormlite.gson_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoParser implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public RepoParser() {
    }

    protected RepoParser(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.url = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final Creator<RepoParser> CREATOR = new Creator<RepoParser>() {
        @Override
        public RepoParser createFromParcel(Parcel source) {
            return new RepoParser(source);
        }

        @Override
        public RepoParser[] newArray(int size) {
            return new RepoParser[size];
        }
    };
}
