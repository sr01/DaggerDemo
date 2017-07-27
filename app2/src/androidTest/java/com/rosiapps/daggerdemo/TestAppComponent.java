package com.rosiapps.daggerdemo;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.main.MainActivityTest;
import com.rosiapps.daggerdemo.main.TestMainBindingModule;
import com.rosiapps.daggerdemo.utils.Scheduler;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        TestMainBindingModule.class,
        TestAppModule.class})
public interface TestAppComponent
{
    void inject(TestApplication application);

    void inject(MainActivityTest activityTest);

    UserRepository userRepository();

    Picasso picasso();

    Scheduler scheduler();

}
