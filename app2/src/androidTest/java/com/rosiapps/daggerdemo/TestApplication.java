package com.rosiapps.daggerdemo;

import android.app.Activity;
import android.app.Application;
import android.support.test.InstrumentationRegistry;

import com.rosiapps.daggerdemo.utils.HasComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */
public class TestApplication extends Application implements HasActivityInjector, HasComponent<TestAppComponent> {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    @Inject
    TestAppComponent component;
    private AndroidInjector<Activity> activityAndroidInjector;

    public static TestApplication getApplication() {
        return (TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerTestAppComponent.builder().build().inject(this);
        activityAndroidInjector = dispatchingActivityInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityAndroidInjector;
    }

    public DispatchingAndroidInjector<Activity> getDispatchingActivityInjector() {
        return dispatchingActivityInjector;
    }

    public void setActivityAndroidInjector(AndroidInjector<Activity> activityAndroidInjector) {
        this.activityAndroidInjector = activityAndroidInjector;
    }

    @Override
    public TestAppComponent getComponent() {
        return component;
    }
}
