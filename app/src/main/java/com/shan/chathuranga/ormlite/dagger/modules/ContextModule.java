package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;

import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChathurangaShan on 1/17/2018.
 */

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @GlobalScope
    @Provides
    public Context getContext(){
        return context;
    }
}
