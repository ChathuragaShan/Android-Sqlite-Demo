package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;

import com.shan.chathuranga.ormlite.dagger.scopes.EventListActivityScope;
import com.shan.chathuranga.ormlite.features.display_event.EventListActivity;
import com.shan.chathuranga.ormlite.features.display_event.EventListAdapter;
import com.shan.chathuranga.ormlite.models.events.gson.EventParser;
import com.shan.chathuranga.ormlite.models.events.ormlight.EventTable;
import com.shan.chathuranga.ormlite.utility.NetworkStatus;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChathurangaShan on 1/27/2018.
 */

@Module
public class EventListModule {

    private Context activityContext;

    public EventListModule(Context activityContext) {
        this.activityContext = activityContext;
    }

    @Provides
    @EventListActivityScope
    public NetworkStatus getNetworkStatus(){
        return new NetworkStatus(activityContext);
    }

    @Provides
    @EventListActivityScope
    public EventListAdapter getEventListAdapter(){
        return new EventListAdapter(activityContext);
    }
}
