package com.rosiapps.daggerdemo;

import android.app.Activity;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by shmulik on 03/08/2017.
 * .
 */
public interface HasMockActivityInjector extends HasActivityInjector
{
    AndroidInjector<Activity> getRealActivityInjector();

    void setMockActivityInjector(AndroidInjector<Activity> activityInjector);
}
