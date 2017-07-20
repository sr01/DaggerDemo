package com.rosiapps.daggerdemo.presentation.main;

import android.util.Log;

import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class MainPresenter implements MainContract.Presenter {
  private static final String TAG = "MainPresenter";
  private MainContract.View view;
  private UserRepository repository;
  private UserRepository.Subscription subscription;
  private Scheduler viewScheduler;
  private MainNavigator navigator;
  private User user;

  @DebugLog
  @Inject
  public MainPresenter(UserRepository repository, Scheduler viewScheduler, MainNavigator navigator) {
    this.repository = repository;
    this.viewScheduler = viewScheduler;
    this.navigator = navigator;
  }

  @DebugLog
  @Override public void bindView(MainContract.View view) {
    this.view = view;

    fetchUserInfo();
  }

  @DebugLog
  @Override public void unbindView() {
    if (subscription != null) {
      subscription.cancel();
    }
  }

  @Override public void showDetails() {
    if(user != null) {
      navigator.gotoUserDetails(user.id);
    }
  }

  @DebugLog
  private void fetchUserInfo() {
    view.showProgress();

    this.subscription = repository.getUser(new UserRepository.Listener<User>() {
      @Override public void onReady(final User user) {
        viewScheduler.execute(new Runnable() {
          @Override public void run() {
            Log.d(TAG, "get user, onReady, user: " + user);
            MainPresenter.this.user = user;
            view.hideProgress();
            view.renderUserInfo(user);
          }
        });
      }
    });
  }
}
