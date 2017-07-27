package com.rosiapps.daggerdemo.main;

import com.rosiapps.daggerdemo.presentation.main.MainActivity;
import com.rosiapps.daggerdemo.presentation.main.MainScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@MainScope
@Subcomponent(modules = {TestMainModule.class})
public interface TestMainComponent extends AndroidInjector<MainActivity>
{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>
    {

    }
}
