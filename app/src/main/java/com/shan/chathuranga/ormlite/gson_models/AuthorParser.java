
package com.shan.chathuranga.ormlite.gson_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorParser implements Parcelable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.name);
    }

    public AuthorParser() {
    }

    protected AuthorParser(Parcel in) {
        this.email = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<AuthorParser> CREATOR = new Parcelable.Creator<AuthorParser>() {
        @Override
        public AuthorParser createFromParcel(Parcel source) {
            return new AuthorParser(source);
        }

        @Override
        public AuthorParser[] newArray(int size) {
            return new AuthorParser[size];
        }
    };
}
