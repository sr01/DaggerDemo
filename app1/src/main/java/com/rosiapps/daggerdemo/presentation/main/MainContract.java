package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.data.User;

public interface MainContract {
  interface View {
    void showProgress();
    void hideProgress();
    void renderUserInfo(User info);
  }

  interface Presenter {
    void bindView(View view);

    void unbindView();
  }
}
