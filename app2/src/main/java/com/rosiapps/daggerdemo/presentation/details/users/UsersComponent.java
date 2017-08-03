package com.rosiapps.daggerdemo.presentation.details.users;

import com.rosiapps.daggerdemo.presentation.details.users.user.UserViewHolder;
import com.rosiapps.daggerdemo.utils.Injector;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = UsersModule.class)
public interface UsersComponent extends AndroidInjector<UsersFragment>, Injector<UserViewHolder> {

    @Override
    void doInject(UserViewHolder viewHolder);

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<UsersFragment> {
    }
}
