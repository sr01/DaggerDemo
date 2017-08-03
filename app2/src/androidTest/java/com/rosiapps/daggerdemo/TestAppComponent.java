package com.rosiapps.daggerdemo;

import com.rosiapps.daggerdemo.presentation.details.TestDetailsBindingModule;
import com.rosiapps.daggerdemo.presentation.main.TestMainBindingModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        TestMainBindingModule.class,
        TestDetailsBindingModule.class,
        TestAppModule.class})
interface TestAppComponent {
    void inject(TestApplication application);
}
