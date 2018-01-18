package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import com.shan.chathuranga.ormlite.R;
import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChathurangaShan on 1/16/2018.
 */
@Module(includes = ContextModule.class)
public class SharedPreferenceModule {

    @GlobalScope
    @Provides
    public SharedPreferences getSharedPreferences(Context context){
        String prefName = context.getResources().getString(R.string.shared_pref_name);
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}
