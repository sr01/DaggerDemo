package com.rosiapps.daggerdemo.presentation.details;

import com.rosiapps.daggerdemo.presentation.details.details.UserDetailsBindingModule;
import com.rosiapps.daggerdemo.presentation.details.locations.LocationsBindingModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@DetailsScope
@Subcomponent(modules = {DetailsModule.class, UserDetailsBindingModule.class, LocationsBindingModule.class})
public interface DetailsComponent extends AndroidInjector<DetailsActivity> {

  @Subcomponent.Builder
  public abstract class Builder extends AndroidInjector.Builder<DetailsActivity> {
    @Override public void seedInstance(DetailsActivity instance) {
      detailsModule(new DetailsModule(instance));
    }

    public abstract DetailsComponent.Builder detailsModule(DetailsModule module);
  }

  ProgressViewInteractor progressViewInteractor();
}
