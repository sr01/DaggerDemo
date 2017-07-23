package com.rosiapps.daggerdemo.presentation.details.users;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {UsersComponent.class})
public abstract class UsersBindingModule {

  @Binds
  @IntoMap
  @dagger.android.support.FragmentKey(UsersFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindUserDetailsFragmentInjectorFactory(UsersComponent.Builder builder);

}
