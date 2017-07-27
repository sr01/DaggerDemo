package com.example.di;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

  DIApplication app();

  void inject(DIApplication app);
}
