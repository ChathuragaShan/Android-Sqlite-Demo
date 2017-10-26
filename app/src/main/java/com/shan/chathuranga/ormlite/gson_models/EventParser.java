
package com.shan.chathuranga.ormlite.gson_models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventParser implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("actor")
    @Expose
    private ActorParser actorParser;

    @SerializedName("repo")
    @Expose
    private RepoParser repoParser;

    @SerializedName("payload")
    @Expose
    private PayloadParser payloadParser;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeParcelable(this.actorParser, flags);
        dest.writeParcelable(this.repoParser, flags);
        dest.writeParcelable(this.payloadParser, flags);
    }

    public EventParser() {
    }

    protected EventParser(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.actorParser = in.readParcelable(ActorParser.class.getClassLoader());
        this.repoParser = in.readParcelable(RepoParser.class.getClassLoader());
        this.payloadParser = in.readParcelable(PayloadParser.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public ActorParser getActorParser() {
        return actorParser;
    }

    public RepoParser getRepoParser() {
        return repoParser;
    }

    public PayloadParser getPayloadParser() {
        return payloadParser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActorParser(ActorParser actorParser) {
        this.actorParser = actorParser;
    }

    public void setRepoParser(RepoParser repoParser) {
        this.repoParser = repoParser;
    }

    public void setPayloadParser(PayloadParser payloadParser) {
        this.payloadParser = payloadParser;
    }

    public static final Creator<EventParser> CREATOR = new Creator<EventParser>() {
        @Override
        public EventParser createFromParcel(Parcel source) {
            return new EventParser(source);
        }

        @Override
        public EventParser[] newArray(int size) {
            return new EventParser[size];
        }
    };
}
