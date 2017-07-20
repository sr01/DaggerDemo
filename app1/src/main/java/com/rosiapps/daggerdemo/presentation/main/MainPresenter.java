package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class MainPresenter implements MainContract.Presenter {
  private MainContract.View view;
  private UserRepository repository;
  private UserRepository.Subscription subscription;
  private Scheduler viewScheduler;

  @DebugLog
  @Inject
  public MainPresenter(UserRepository repository, Scheduler viewScheduler) {
    this.repository = repository;
    this.viewScheduler = viewScheduler;
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

  @DebugLog
  private void fetchUserInfo() {
    view.showProgress();

    this.subscription = repository.getUser(new UserRepository.Listener() {
      @Override public void onUserReady(final User userInfo) {
        viewScheduler.execute(new Runnable() {
          @Override public void run() {
            view.hideProgress();
            view.renderUserInfo(userInfo);
          }
        });
      }
    });
  }
}
