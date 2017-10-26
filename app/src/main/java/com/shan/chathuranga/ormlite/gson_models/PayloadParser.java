
package com.shan.chathuranga.ormlite.gson_models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayloadParser implements Parcelable {

    @SerializedName("push_id")
    @Expose
    private Integer pushId;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("distinct_size")
    @Expose
    private Integer distinctSize;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("head")
    @Expose
    private String head;
    @SerializedName("before")
    @Expose
    private String before;
    @SerializedName("commits")
    @Expose
    private List<CommitParser> commitParsers = null;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("ref_type")
    @Expose
    private String refType;
    @SerializedName("master_branch")
    @Expose
    private String masterBranch;
    @SerializedName("pusher_type")
    @Expose
    private String pusherType;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pushId);
        dest.writeValue(this.size);
        dest.writeValue(this.distinctSize);
        dest.writeString(this.ref);
        dest.writeString(this.head);
        dest.writeString(this.before);
        dest.writeList(this.commitParsers);
        dest.writeString(this.action);
        dest.writeString(this.refType);
        dest.writeString(this.masterBranch);
        dest.writeString(this.pusherType);
    }

    public PayloadParser() {
    }

    protected PayloadParser(Parcel in) {
        this.pushId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.size = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distinctSize = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ref = in.readString();
        this.head = in.readString();
        this.before = in.readString();
        this.commitParsers = new ArrayList<CommitParser>();
        in.readList(this.commitParsers, CommitParser.class.getClassLoader());
        this.action = in.readString();
        this.refType = in.readString();
        this.masterBranch = in.readString();
        this.pusherType = in.readString();
    }

    public static final Parcelable.Creator<PayloadParser> CREATOR = new Parcelable.Creator<PayloadParser>() {
        @Override
        public PayloadParser createFromParcel(Parcel source) {
            return new PayloadParser(source);
        }

        @Override
        public PayloadParser[] newArray(int size) {
            return new PayloadParser[size];
        }
    };

    public Integer getPushId() {
        return pushId;
    }

    public void setPushId(Integer pushId) {
        this.pushId = pushId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDistinctSize() {
        return distinctSize;
    }

    public void setDistinctSize(Integer distinctSize) {
        this.distinctSize = distinctSize;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<CommitParser> getCommitParsers() {
        return commitParsers;
    }

    public void setCommitParsers(List<CommitParser> commitParsers) {
        this.commitParsers = commitParsers;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getMasterBranch() {
        return masterBranch;
    }

    public void setMasterBranch(String masterBranch) {
        this.masterBranch = masterBranch;
    }

    public String getPusherType() {
        return pusherType;
    }

    public void setPusherType(String pusherType) {
        this.pusherType = pusherType;
    }
}
