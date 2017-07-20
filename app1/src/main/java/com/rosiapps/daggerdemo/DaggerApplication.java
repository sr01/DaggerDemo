package com.rosiapps.daggerdemo;

import android.app.Application;

public class DaggerApplication extends Application implements HasComponent<AppComponent> {

  private AppComponent appComponent;


  @Override public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  @Override public AppComponent getComponent() {
    return appComponent;
  }
}
