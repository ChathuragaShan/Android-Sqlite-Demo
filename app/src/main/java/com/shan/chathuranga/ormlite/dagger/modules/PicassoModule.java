package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ChathurangaShan on 1/18/2018.
 */
@Module(includes = NetworkModule.class)
public class PicassoModule {

    @Provides
    @GlobalScope
    public Picasso getPicasso(Context context,OkHttp3Downloader okHttp3Downloader){
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(okHttp3Downloader);
        return builder.build();
    }

}
