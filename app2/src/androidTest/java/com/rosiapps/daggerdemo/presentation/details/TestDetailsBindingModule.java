package com.rosiapps.daggerdemo.presentation.details;

import android.app.Activity;

import com.rosiapps.daggerdemo.presentation.main.MainActivity;
import com.rosiapps.daggerdemo.presentation.main.TestMainSubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = TestDetailsComponent.class)
public abstract class TestDetailsBindingModule
{
  @Binds
  @IntoMap
  @ActivityKey(DetailsActivity.class)
  abstract AndroidInjector.Factory<? extends Activity>  bindDetailsActivityInjectorFactory(TestDetailsComponent.Builder builder);

}
