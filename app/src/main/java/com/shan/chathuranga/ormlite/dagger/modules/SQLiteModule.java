package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;
import com.shan.chathuranga.ormlite.DatabaseHelper;
import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChathurangaShan on 1/17/2018.
 */
@Module(includes = ContextModule.class)
public class SQLiteModule {

    @Provides
    @GlobalScope
    public DatabaseHelper getSQLite(Context context){
        return new DatabaseHelper(context);
    }
}
