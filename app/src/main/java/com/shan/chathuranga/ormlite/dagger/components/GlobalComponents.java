package com.shan.chathuranga.ormlite.dagger.components;



import com.shan.chathuranga.ormlite.APIService;
import com.shan.chathuranga.ormlite.DatabaseHelper;
import com.shan.chathuranga.ormlite.dagger.modules.PicassoModule;
import com.shan.chathuranga.ormlite.dagger.modules.SQLiteModule;
import com.shan.chathuranga.ormlite.dagger.modules.SharedPreferenceModule;
import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;
import com.shan.chathuranga.ormlite.features.display_event.EventListActivity;

import dagger.Component;

/**
 * Created by ChathurangaShan on 1/14/2018.
 */

@GlobalScope
@Component(modules = {SharedPreferenceModule.class, SQLiteModule.class, PicassoModule.class})
public interface GlobalComponents {

    //void inject(EventListActivity mainActivity);

    DatabaseHelper databaseHelper();

    APIService apiService();
}
