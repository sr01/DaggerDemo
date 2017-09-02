package com.rosiapps.daggerdemo;

import android.app.Application;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.details.DetailsBindingModule;
import com.rosiapps.daggerdemo.presentation.main.MainBindingModule;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainBindingModule.class,
        DetailsBindingModule.class})
interface AppComponent extends AndroidInjector<DemoApplication> {

    @Override
    void inject(DemoApplication application);

    UserRepository userRepository();

    Picasso picasso();

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
