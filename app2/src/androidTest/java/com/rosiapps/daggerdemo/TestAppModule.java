package com.rosiapps.daggerdemo;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;
import com.rosiapps.daggerdemo.utils.Schedulers;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */
@Module
public class TestAppModule
{
    @Singleton
    @Provides
    public UserRepository provideUserRepository()
    {
        return Mockito.mock(UserRepository.class);
    }

    @Singleton
    @Provides
    public Picasso providePicasso()
    {
        Picasso picasso = Mockito.mock(Picasso.class);
        RequestCreator mockRequestCreator = Mockito.mock(RequestCreator.class);
        when(picasso.load(anyString())).thenReturn(mockRequestCreator);
        return picasso;
    }

    @Singleton
    @Provides
    public Scheduler provideViewScheduler()
    {
        return Schedulers.androidMainScheduler();
    }
}
