package com.rosiapps.daggerdemo.presentation.details.users;

import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserViewHolder;
import com.rosiapps.daggerdemo.utils.Injector;
import com.rosiapps.daggerdemo.utils.Scheduler;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {


    @Provides
    public Injector<UserViewHolder> userViewHolderInjector(UsersComponent component){
        return component;
    }

    @Provides
    public UsersContract.Presenter providePresenter(UserRepository repository, Scheduler viewScheduler) {
        return new UsersPresenter(repository, viewScheduler);
    }
}
