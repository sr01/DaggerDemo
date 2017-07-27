package com.rosiapps.daggerdemo.main;

import android.app.Activity;

import com.rosiapps.daggerdemo.presentation.main.MainActivity;
import com.rosiapps.daggerdemo.presentation.main.MainComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = TestMainComponent.class)
public abstract class TestMainBindingModule
{
  @Binds
  @IntoMap
  @ActivityKey(MainActivity.class)
  abstract AndroidInjector.Factory<? extends Activity>
  bindMainActivityInjectorFactory(TestMainComponent.Builder builder);
}
