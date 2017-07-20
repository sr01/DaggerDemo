package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.AppComponent;

import dagger.Component;

@MainScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
  void inject(MainActivity activity);
}
