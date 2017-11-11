package com.shan.chathuranga.ormlite.models.events.ormlight;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by ChathurangaShan on 10/15/2017.
 */

@DatabaseTable(tableName = EventTable.TABLE_NAME)
public class EventTable {

    public static final String TABLE_NAME = "event";

    @DatabaseField(id = true,columnName = "event_id")
    private String eventId;

    @DatabaseField
    private String type;

    @DatabaseField(columnName = "actor_id" ,foreign =true, foreignColumnName = "actor_id", foreignAutoRefresh=true)
    private ActorTable actorTable;

    @DatabaseField(columnName = "repo_id", foreign =true, foreignColumnName = "repo_id", foreignAutoRefresh=true)
    private RepoTable repoTable;

    @ForeignCollectionField(eager = true)
    private Collection<CommitTable> commitList;

    public String getEventId() {
        return eventId;
    }

    public String getType() {
        return type;
    }

    public ActorTable getActorTable() {
        return actorTable;
    }

    public RepoTable getRepoTable() {
        return repoTable;
    }

    public Collection<CommitTable> getCommitList() {
        return commitList;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setActorTable(ActorTable actorTable) {
        this.actorTable = actorTable;
    }

    public void setRepoTable(RepoTable repoTable) {
        this.repoTable = repoTable;
    }

    public void setCommitList(Collection<CommitTable> commitList) {
        this.commitList = commitList;
    }
}
