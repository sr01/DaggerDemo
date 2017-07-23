package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;

@Module
public class MainModule {

    private MainNavigator navigator;
    private String userId;

    @DebugLog
    public MainModule(MainNavigator navigator, String userId) {
        this.navigator = navigator;
        this.userId = userId;
    }

    @MainScope
    @Provides
    public MainContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler) {
        return new MainPresenter(userId, repository, viewScheduler, navigator);
    }
}
