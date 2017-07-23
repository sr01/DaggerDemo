package com.rosiapps.daggerdemo.presentation.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        @Override
        public void seedInstance(MainActivity instance) {
            mainModule(new MainModule(instance, "XW 53 74 08 Y"));
        }

        public abstract Builder mainModule(MainModule module);
    }
}
