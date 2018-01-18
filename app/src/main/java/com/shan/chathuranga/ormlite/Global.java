package com.shan.chathuranga.ormlite;

import android.app.Application;

import com.shan.chathuranga.ormlite.dagger.components.DaggerGlobalComponents;
import com.shan.chathuranga.ormlite.dagger.components.GlobalComponents;
import com.shan.chathuranga.ormlite.dagger.modules.ContextModule;

/**
 * Created by ChathurangaShan on 10/17/2017.
 */

public class Global extends Application {

    private static Global application;
    private GlobalComponents globalComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        globalComponent = DaggerGlobalComponents.builder()
                .contextModule(new ContextModule(application))
                .build();

    }

    public static Global application(){
        return application;
    }

    public GlobalComponents getGlobalComponent() {
        return globalComponent;
    }
}
