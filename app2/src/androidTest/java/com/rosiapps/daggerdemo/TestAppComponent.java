package com.rosiapps.daggerdemo;

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
        TestAppModule.class})
public interface TestAppComponent {
    void inject(TestApplication application);
}
