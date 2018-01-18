package com.shan.chathuranga.ormlite.dagger.modules;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.shan.chathuranga.ormlite.APIService;
import com.shan.chathuranga.ormlite.dagger.scopes.GlobalScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ChathurangaShan on 1/17/2018.
 */

@Module(includes = ContextModule.class)
public class NetworkModule {

    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    @GlobalScope
    public Retrofit getRetrofit(OkHttpClient okHttpClient,GsonConverterFactory gsonConverterFactory,
                                RxJava2CallAdapterFactory rxJava2CallAdapterFactory){
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @GlobalScope
    public OkHttpClient getOkHttpClient(StethoInterceptor getInterceptor){
       return new OkHttpClient.Builder()
                .addNetworkInterceptor(getInterceptor)
                .build();
    }

    @Provides
    @GlobalScope
    public StethoInterceptor getInterceptor(){
        return new StethoInterceptor();
    }

    @Provides
    @GlobalScope
    public GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @GlobalScope
    public APIService getApiService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }

    @Provides
    @GlobalScope
    public OkHttp3Downloader getOkHttp3Downloader(Context context){
        return new OkHttp3Downloader(context);
    }

    @Provides
    @GlobalScope
    public RxJava2CallAdapterFactory getRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

}
