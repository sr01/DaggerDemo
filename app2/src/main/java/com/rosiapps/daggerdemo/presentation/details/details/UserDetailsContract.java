package com.rosiapps.daggerdemo.presentation.details.details;

import com.rosiapps.daggerdemo.data.UserDetails;

public interface UserDetailsContract {
  interface View {
    void showProgress();
    void hideProgress();
    void renderUserDetails(UserDetails details);
  }

  interface Presenter {
    void bindView(View view);
    void unbindView();
  }
}
