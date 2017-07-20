package com.rosiapps.daggerdemo.presentation.details.details;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import dagger.Module;
import dagger.Provides;

@Module
public class UserDetailsModule {

  private String userId;

  public UserDetailsModule(String userId) {
    this.userId = userId;
  }

  @Provides public UserDetailsContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler) {
    return new UserDetailsPresenter(userId, repository, viewScheduler);
  }
}
