package com.rosiapps.daggerdemo.presentation.details.details;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = UserDetailsModule.class)
public interface UserDetailsComponent extends AndroidInjector<UserDetailsFragment> {

  @Subcomponent.Builder
  public abstract class Builder extends AndroidInjector.Builder<UserDetailsFragment> {
    @Override public void seedInstance(UserDetailsFragment instance) {
      String userId = instance.getUserId();
      detailsModule(new UserDetailsModule(userId));
    }

    protected abstract Builder detailsModule(UserDetailsModule module);
  }
}
