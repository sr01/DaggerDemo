package com.rosiapps.daggerdemo;

import android.app.Activity;
import android.app.Application;
import android.support.test.InstrumentationRegistry;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */
public class TestApplication extends Application implements HasActivityInjector, HasMockActivityInjector
{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    private AndroidInjector<Activity> mockActivityInjector;

    public static TestApplication getApplication()
    {
        return (TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        DaggerTestAppComponent.builder().build().inject(this);
        mockActivityInjector = dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector()
    {
        return mockActivityInjector;
    }

    @Override
    public AndroidInjector<Activity> getRealActivityInjector()
    {
        return dispatchingActivityInjector;
    }

    @Override
    public void setMockActivityInjector(AndroidInjector<Activity> activityInjector)
    {
        mockActivityInjector = activityInjector;
    }
}
