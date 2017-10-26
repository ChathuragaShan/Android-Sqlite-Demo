package com.shan.chathuranga.ormlite.ormlight_models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ChathurangaShan on 10/15/2017.
 */

@DatabaseTable(tableName = CommitTable.TABLE_NAME)
public class CommitTable {

    public static final String TABLE_NAME = "commit";

    @DatabaseField(id = true,columnName = "commit_id")
    private int commitId;

    @DatabaseField(columnName = "messsage")
    private String message;

    @DatabaseField(columnName = "distinct")
    private String distinct;

    @DatabaseField(columnName = "event_id", foreign = true, foreignColumnName = "event_id")
    private EventTable eventId;

    public int getCommitId() {
        return commitId;
    }

    public String getMessage() {
        return message;
    }

    public String getDistinct() {
        return distinct;
    }

    public EventTable getEvent() {
        return eventId;
    }

    public void setCommitId(int commitId) {
        this.commitId = commitId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public void setEvent(EventTable eventId) {
        this.eventId = eventId;
    }
}
