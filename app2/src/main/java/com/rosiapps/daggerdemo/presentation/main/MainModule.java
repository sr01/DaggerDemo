package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;

@Module
public class MainModule {

  private MainNavigator navigator;

  @DebugLog
  public MainModule(MainNavigator navigator) {
    this.navigator = navigator;
  }

  @MainScope
  @Provides public MainContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler) {
    return new MainPresenter(repository, viewScheduler, navigator);
  }
}
