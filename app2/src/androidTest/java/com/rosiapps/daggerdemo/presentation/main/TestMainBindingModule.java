package com.rosiapps.daggerdemo.presentation.main;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = TestMainSubcomponent.class)
public abstract class TestMainBindingModule
{
  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity>  bindMainActivityInjectorFactory(TestMainSubcomponent.Builder builder);

}
