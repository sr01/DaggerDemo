package com.rosiapps.daggerdemo;

import android.app.Activity;
import android.app.Application;

import com.rosiapps.daggerdemo.utils.HasComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class DaggerApplication extends Application implements HasComponent<AppComponent>, HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
  private AppComponent appComponent;


  @Override public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();

    appComponent.inject(this);
  }

  @Override public AppComponent getComponent() {
    return appComponent;
  }

  @Override public AndroidInjector<Activity> activityInjector() {
    return dispatchingActivityInjector;
  }

}
