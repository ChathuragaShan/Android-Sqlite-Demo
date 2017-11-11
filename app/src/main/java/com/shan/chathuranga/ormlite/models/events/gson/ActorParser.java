
package com.shan.chathuranga.ormlite.models.events.gson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorParser implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("display_login")
    @Expose
    private String displayLogin;

    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.login);
        dest.writeString(this.displayLogin);
        dest.writeString(this.gravatarId);
        dest.writeString(this.url);
        dest.writeString(this.avatarUrl);
    }

    public ActorParser() {
    }

    protected ActorParser(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.login = in.readString();
        this.displayLogin = in.readString();
        this.gravatarId = in.readString();
        this.url = in.readString();
        this.avatarUrl = in.readString();
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public static final Creator<ActorParser> CREATOR = new Creator<ActorParser>() {
        @Override
        public ActorParser createFromParcel(Parcel source) {
            return new ActorParser(source);
        }

        @Override
        public ActorParser[] newArray(int size) {
            return new ActorParser[size];
        }
    };
}
