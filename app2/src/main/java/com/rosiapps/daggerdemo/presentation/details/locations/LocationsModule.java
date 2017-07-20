package com.rosiapps.daggerdemo.presentation.details.locations;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationsModule {

  private String userId;

  public LocationsModule(String userId) {
    this.userId = userId;
  }

  @Provides public LocationsContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler) {
    return new LocationsPresenter(userId, repository, viewScheduler);
  }
}
