package com.rosiapps.daggerdemo;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

  UserRepository userRepository();

  Picasso picasso();
}
