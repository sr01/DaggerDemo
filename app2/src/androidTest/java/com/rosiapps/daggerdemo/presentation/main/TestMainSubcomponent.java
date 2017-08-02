package com.rosiapps.daggerdemo.presentation.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@MainScope
@Subcomponent(modules = {TestMainModule.class})
public interface TestMainSubcomponent extends AndroidInjector<MainActivity>
{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>
    {

    }
}
