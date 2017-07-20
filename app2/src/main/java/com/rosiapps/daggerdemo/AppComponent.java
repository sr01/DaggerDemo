package com.rosiapps.daggerdemo;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.details.DetailsBindingModule;
import com.rosiapps.daggerdemo.presentation.main.MainBindingModule;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
    AndroidInjectionModule.class,
    AppModule.class,
    MainBindingModule.class,
    DetailsBindingModule.class})
public interface AppComponent {

  void inject(DaggerApplication application);

  UserRepository userRepository();

  Picasso picasso();
}
