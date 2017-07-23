package com.rosiapps.daggerdemo.presentation.details.users.user;

import com.rosiapps.daggerdemo.data.User;

/**
 * Created by Shmulik on 24/07/2017.
 * .
 */
public class UserPresenter implements UserContract.Presenter {
    private UserContract.View view;
    private User model;

    public UserPresenter(User model) {
        this.model = model;
    }

    private void updateView() {
        view.renderName(model.firstName + " " + model.lastName);
        view.renderDetails("Email: " + model.email + "\nID: " + model.id);
        view.renderPicture(model.photoUrl);
    }

    @Override
    public void setModel(User model) {
        this.model = model;
        updateView();
    }

    @Override
    public void bindView(UserContract.View view) {
        this.view = view;
        updateView();
    }

    @Override
    public void unbindView() {
        this.model = null;
        this.view = null;
    }
}
