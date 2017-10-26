package com.shan.chathuranga.ormlite.ormlight_models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ChathurangaShan on 10/15/2017.
 */
@DatabaseTable(tableName = RepoTable.TABLE_NAME)
public class RepoTable {

    public static final String TABLE_NAME = "repo";

    @DatabaseField(id = true,columnName = "repo_id")
    private long repoId;

    @DatabaseField(columnName = "name")
    private String name;

    public long getRepoId() {
        return repoId;
    }

    public String getName() {
        return name;
    }

    public void setRepoId(long repoId) {
        this.repoId = repoId;
    }

    public void setName(String name) {
        this.name = name;
    }

}
