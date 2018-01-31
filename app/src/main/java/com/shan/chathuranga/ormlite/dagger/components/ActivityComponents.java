package com.shan.chathuranga.ormlite.dagger.components;

import com.shan.chathuranga.ormlite.dagger.modules.EventListModule;
import com.shan.chathuranga.ormlite.dagger.scopes.EventListActivityScope;
import com.shan.chathuranga.ormlite.features.display_event.EventListActivity;
import com.shan.chathuranga.ormlite.utility.NetworkStatus;

import dagger.Component;

/**
 * Created by ChathurangaShan on 1/27/2018.
 */

@EventListActivityScope
@Component(modules = EventListModule.class, dependencies = GlobalComponents.class)
public interface ActivityComponents {

    //NetworkStatus networkStatus();

    void inject(EventListActivity activity);

}
