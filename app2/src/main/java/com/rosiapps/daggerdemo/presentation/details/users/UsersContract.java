package com.rosiapps.daggerdemo.presentation.details.users;

import com.rosiapps.daggerdemo.presentation.details.users.user.UserPresenter;

import java.util.List;

public interface UsersContract {
    interface View {
        void showProgress();

        void hideProgress();

        void renderUsers(List<UserPresenter> users);
    }

    interface Presenter {
        void bindView(View view);

        void unbindView();
    }
}
