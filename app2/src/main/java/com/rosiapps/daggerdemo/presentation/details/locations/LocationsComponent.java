package com.rosiapps.daggerdemo.presentation.details.locations;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = LocationsModule.class)
public interface LocationsComponent extends AndroidInjector<LocationsFragment> {

  @Subcomponent.Builder
  public abstract class Builder extends AndroidInjector.Builder<LocationsFragment> {
    @Override public void seedInstance(LocationsFragment instance) {
      String userId = instance.getUserId();
      locationsModule(new LocationsModule(userId));
    }

    protected abstract Builder locationsModule(LocationsModule module);
  }
}
