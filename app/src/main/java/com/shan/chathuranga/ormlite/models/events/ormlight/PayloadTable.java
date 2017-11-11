package com.shan.chathuranga.ormlite.models.events.ormlight;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ChathurangaShan on 10/16/2017.
 */

@DatabaseTable(tableName = PayloadTable.TABLE_NAME)
public class PayloadTable {

    public static final String TABLE_NAME = "payload";

    @DatabaseField(id = true, columnName = "payload_id")
    private int id;

    @DatabaseField(columnName = "commit_id")
    private int commitId;

    public int getId() {
        return id;
    }

    public int getCommitId() {
        return commitId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommitId(int commitId) {
        this.commitId = commitId;
    }
}
