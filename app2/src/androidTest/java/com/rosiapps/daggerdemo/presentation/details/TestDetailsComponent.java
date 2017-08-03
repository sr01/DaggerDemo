package com.rosiapps.daggerdemo.presentation.details;

import com.rosiapps.daggerdemo.presentation.details.users.UsersFragmentTest;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@DetailsScope
@Subcomponent(modules = {TestDetailsModule.class, UsersFragmentTest.Injection.TestUsersBindingModule.class})
public interface TestDetailsComponent extends AndroidInjector<DetailsActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DetailsActivity> {
    }
}
