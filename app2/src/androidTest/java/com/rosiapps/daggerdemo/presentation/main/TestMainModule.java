package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;

@Module
class TestMainModule
{
    @DebugLog
    public TestMainModule()
    {
    }

    @MainScope
    @Provides
    static MainContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler, MainNavigator navigator)
    {
        return new MainPresenter("1", repository, viewScheduler, navigator);
    }

    @MainScope
    @Provides
    static MainNavigator provideMainNavigator()
    {
        return Mockito.mock(MainNavigator.class);
    }
}
