package com.shan.chathuranga.ormlite.models.events.ormlight;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ChathurangaShan on 10/15/2017.
 */

@DatabaseTable(tableName = ActorTable.TABLE_NAME)
public class ActorTable {

    public static final String TABLE_NAME = "actor";

    @DatabaseField(id = true, columnName = "actor_id")
    private int actorID;

    @DatabaseField(columnName = "display_login")
    private String displayLogin;

    @DatabaseField(columnName = "image_path")
    private String imagePath = "N/A";

    @DatabaseField(columnName = "commit_id")
    private String commitId;

    public long getActorID() {
        return actorID;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }
}
