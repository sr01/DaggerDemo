package com.rosiapps.daggerdemo.main;

import com.rosiapps.daggerdemo.AppComponent;

import dagger.Component;

@MainScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
  void inject(MainActivity activity);
}
