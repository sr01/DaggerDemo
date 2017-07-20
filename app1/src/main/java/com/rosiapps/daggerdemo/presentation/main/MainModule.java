package com.rosiapps.daggerdemo.presentation.main;

import android.content.Context;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Schedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

  private Context context;

  public MainModule(Context context) {
    this.context = context;
  }

  @MainScope
  @Provides public Context provideContext() {
    return context;
  }

  @MainScope
  @Provides public MainContract.Presenter providePresenter(UserRepository repository) {
    return new MainPresenter(repository, Schedulers.androidMainScheduler());
  }
}
