
package com.shan.chathuranga.ormlite.models.events.gson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CommitParser implements Parcelable {

    @SerializedName("sha")
    @Expose
    private String sha;

    @SerializedName("author")
    @Expose
    private AuthorParser authorParser;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("distinct")
    @Expose
    private Boolean distinct;

    @SerializedName("url")
    @Expose
    private String url;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sha);
        dest.writeParcelable(this.authorParser, flags);
        dest.writeString(this.message);
        dest.writeValue(this.distinct);
        dest.writeString(this.url);
    }

    public CommitParser() {
    }

    protected CommitParser(Parcel in) {
        this.sha = in.readString();
        this.authorParser = in.readParcelable(AuthorParser.class.getClassLoader());
        this.message = in.readString();
        this.distinct = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.url = in.readString();
    }

    public String getSha() {
        return sha;
    }

    public AuthorParser getAuthorParser() {
        return authorParser;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public String getUrl() {
        return url;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public void setAuthorParser(AuthorParser authorParser) {
        this.authorParser = authorParser;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final Creator<CommitParser> CREATOR = new Creator<CommitParser>() {
        @Override
        public CommitParser createFromParcel(Parcel source) {
            return new CommitParser(source);
        }

        @Override
        public CommitParser[] newArray(int size) {
            return new CommitParser[size];
        }
    };
}
