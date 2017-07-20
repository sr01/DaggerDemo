package com.example;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

  Application app();

  void inject(Application app);
}
