package com.rosiapps.daggerdemo.main;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.main.MainContract;
import com.rosiapps.daggerdemo.presentation.main.MainNavigator;
import com.rosiapps.daggerdemo.presentation.main.MainPresenter;
import com.rosiapps.daggerdemo.presentation.main.MainScope;
import com.rosiapps.daggerdemo.utils.Scheduler;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;

@Module
public class TestMainModule
{
    @DebugLog
    public TestMainModule()
    {
    }

    @MainScope
    @Provides
    public MainContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler, MainNavigator navigator)
    {
        return new MainPresenter("1", repository, viewScheduler, navigator);
    }

    @MainScope
    @Provides
    public MainNavigator provideMainNavigator()
    {
        return Mockito.mock(MainNavigator.class);
    }

}
