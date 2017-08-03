package com.rosiapps.daggerdemo.presentation.details;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shmulik on 03/08/2017.
 * .
 */
@Module
public class TestDetailsModule {

    @DetailsScope
    @Provides
    ProgressViewInteractor provideProgressViewInteractor() {
        return Mockito.mock(ProgressViewInteractor.class);
    }
}
