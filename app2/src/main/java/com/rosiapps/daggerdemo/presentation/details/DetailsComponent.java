package com.rosiapps.daggerdemo.presentation.details;

import com.rosiapps.daggerdemo.presentation.details.users.UsersBindingModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@DetailsScope
@Subcomponent(modules = {DetailsModule.class, UsersBindingModule.class})
public interface DetailsComponent extends AndroidInjector<DetailsActivity> {

    ProgressViewInteractor progressViewInteractor();

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<DetailsActivity> {
        @Override
        public void seedInstance(DetailsActivity instance) {
            detailsModule(new DetailsModule(instance));
        }

        public abstract DetailsComponent.Builder detailsModule(DetailsModule module);
    }
}
