package com.rosiapps.daggerdemo.presentation.details;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {DetailsComponent.class})
public abstract class DetailsBindingModule {

  @Binds
  @IntoMap
  @ActivityKey(DetailsActivity.class)
  abstract AndroidInjector.Factory<? extends Activity>
  bindDetailsActivityInjectorFactory(DetailsComponent.Builder builder);

}
