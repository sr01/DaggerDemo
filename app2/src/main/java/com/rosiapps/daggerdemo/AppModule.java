package com.rosiapps.daggerdemo;

import android.content.Context;

import com.rosiapps.daggerdemo.data.SimpleUserRepository;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.main.MainComponent;
import com.rosiapps.daggerdemo.utils.Scheduler;
import com.rosiapps.daggerdemo.utils.Schedulers;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {MainComponent.class})
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public UserRepository provideUserRepository() {
        return new SimpleUserRepository(context);
    }

    @Singleton
    @Provides
    public Picasso providePicasso() {
        return new Picasso.Builder(context).build();
    }

    @Singleton
    @Provides
    public Scheduler provideViewScheduler() {
        return Schedulers.androidMainScheduler();
    }
}
