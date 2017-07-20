package com.rosiapps.daggerdemo.presentation.details;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

  private ProgressViewInteractor interactor;

  public DetailsModule(ProgressViewInteractor interactor) {
    this.interactor = interactor;
  }

  @DetailsScope
  @Provides
  public ProgressViewInteractor provideProgressViewInteractor() {
    return interactor;
  }
}
