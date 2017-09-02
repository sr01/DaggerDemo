package com.rosiapps.daggerdemo;

import android.app.Application;
import android.content.Context;

import com.rosiapps.daggerdemo.data.SimpleUserRepository;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.main.MainComponent;
import com.rosiapps.daggerdemo.utils.Scheduler;
import com.rosiapps.daggerdemo.utils.Schedulers;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(subcomponents = {MainComponent.class})
public abstract class AppModule {

    @Singleton
    @Provides
    static Picasso providePicasso(Context context) {
        return new Picasso.Builder(context).build();
    }

    @Singleton
    @Provides
    static Scheduler provideViewScheduler() {
        return Schedulers.androidMainScheduler();
    }

    @Binds
    public abstract Context context(Application application);

    @Singleton
    @Binds
    abstract UserRepository provideUserRepository(SimpleUserRepository repository);
}
