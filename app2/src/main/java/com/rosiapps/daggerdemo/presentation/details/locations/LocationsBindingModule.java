package com.rosiapps.daggerdemo.presentation.details.locations;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {LocationsComponent.class})
public abstract class LocationsBindingModule {
  @Binds
  @IntoMap
  @dagger.android.support.FragmentKey(LocationsFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindLocationsFragmentInjectorFactory(LocationsComponent.Builder builder);
}
