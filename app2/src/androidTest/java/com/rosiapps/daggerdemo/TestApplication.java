package com.rosiapps.daggerdemo;

import android.app.Activity;
import android.app.Application;

import com.rosiapps.daggerdemo.utils.HasComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */
public class TestApplication extends Application implements HasActivityInjector, HasComponent<TestAppComponent>
{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject TestAppComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();

        DaggerTestAppComponent.builder().build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector()
    {
        return dispatchingActivityInjector;
    }

    @Override
    public TestAppComponent getComponent()
    {
        return component;
    }
}
