package com.rosiapps.daggerdemo.presentation.details.details;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {UserDetailsComponent.class})
public abstract class UserDetailsBindingModule {

  @Binds
  @IntoMap
  @dagger.android.support.FragmentKey(UserDetailsFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindUserDetailsFragmentInjectorFactory(UserDetailsComponent.Builder builder);

}
