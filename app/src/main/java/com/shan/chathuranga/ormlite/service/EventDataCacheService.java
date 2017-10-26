package com.shan.chathuranga.ormlite.service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.shan.chathuranga.ormlite.DatabaseHelper;
import com.shan.chathuranga.ormlite.gson_models.ActorParser;
import com.shan.chathuranga.ormlite.gson_models.CommitParser;
import com.shan.chathuranga.ormlite.gson_models.EventParser;
import com.shan.chathuranga.ormlite.gson_models.RepoParser;
import com.shan.chathuranga.ormlite.ormlight_models.ActorTable;
import com.shan.chathuranga.ormlite.ormlight_models.CommitTable;
import com.shan.chathuranga.ormlite.ormlight_models.EventTable;
import com.shan.chathuranga.ormlite.ormlight_models.RepoTable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventDataCacheService extends IntentService {

    private static final String TAG = EventDataCacheService.class.getSimpleName();

    public EventDataCacheService() {
        super("EventDataCacheService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<EventParser> eventParserData = intent.getParcelableArrayListExtra("event_data");
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            for (EventParser eventParserObj : eventParserData){
                try {

                    //Save actorParser data
                    ActorParser actorParser = eventParserObj.getActorParser();
                    ActorTable actorTable = new ActorTable();
                    actorTable.setActorID(actorParser.getId());
                    actorTable.setDisplayLogin(actorParser.getDisplayLogin());
                    actorTable.setCommitId(eventParserObj.getId());
                    actorTable.setImagePath(actorParser.getAvatarUrl());
                    dbHelper.createOrUpdate(actorTable);

                    //Save repoParser data
                    RepoParser repoParser = eventParserObj.getRepoParser();
                    RepoTable repoTable = new RepoTable();
                    repoTable.setRepoId(repoParser.getId());
                    repoTable.setName(repoParser.getName());
                    dbHelper.createOrUpdate(repoTable);

                    // Save Event data
                    EventTable eventTable = new EventTable();
                    eventTable.setEventId(eventParserObj.getId());
                    eventTable.setType(eventParserObj.getType());
                    eventTable.setActorTable(actorTable);
                    eventTable.setRepoTable(repoTable);

                    //Save commit data
                    List<CommitParser> commitParsers = eventParserObj.getPayloadParser()
                            .getCommitParsers();

                    Collection<CommitTable> commitCollection = new ArrayList<>();

                    for (CommitParser commitObj: commitParsers){
                        CommitTable maxIdROw = dbHelper.getMaxId(CommitTable.class, "commit_id", false);
                        int commitId;
                        if(maxIdROw == null){
                            commitId =  1;
                        }else{
                            commitId = maxIdROw.getCommitId() + 1;
                        }

                        CommitTable commitTable = new CommitTable();
                        commitTable.setCommitId(commitId);
                        commitTable.setMessage(commitObj.getMessage());
                        commitTable.setDistinct(commitObj.getDistinct().toString());
                        commitTable.setEvent(eventTable);
                        dbHelper.createOrUpdate(commitTable);
                        commitCollection.add(commitTable);
                    }


                    eventTable.setCommitList(commitCollection);
                    dbHelper.createOrUpdate(eventTable);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            dbHelper.close();

            /*for (EventParser eventParserObj : eventParserData){
                ActorParser actorParser = eventParserObj.getActorParser();
                Integer actorID = actorParser.getId();
                String avatarUrl = actorParser.getAvatarUrl();
                downloadImages(avatarUrl,actorID);

            }*/
        }
    }

    public void downloadImages(String imageURL, final int actorId){

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                File sd = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File folder = new File(sd, "/eventCacheImages/");
                if (!folder.exists()) {
                    if (!folder.mkdir()) {
                        Log.e("ERROR", "Cannot create a directory!");
                    } else {
                        folder.mkdir();
                    }
                }

                File imageFile = new File(folder,String.valueOf(actorId));
                try {

                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.close();
                    if(compress){
                        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                        ActorTable actorTable = new ActorTable();
                        actorTable.setActorID(actorId);
                        actorTable.setImagePath(imageFile.getPath());
                        try {
                            dbHelper.createOrUpdate(actorTable);
                            dbHelper.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.with(getApplicationContext()).load(imageURL).into(target);
    }
}
