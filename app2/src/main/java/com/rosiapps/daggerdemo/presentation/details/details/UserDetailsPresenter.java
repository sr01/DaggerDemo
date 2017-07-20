package com.rosiapps.daggerdemo.presentation.details.details;

import com.rosiapps.daggerdemo.data.UserDetails;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class UserDetailsPresenter implements UserDetailsContract.Presenter {
  private UserDetailsContract.View view;
  private UserRepository repository;
  private UserRepository.Subscription subscription;
  private Scheduler viewScheduler;
  private String userId;

  @DebugLog
  @Inject
  public UserDetailsPresenter(String userId, UserRepository repository, Scheduler viewScheduler) {
    this.userId = userId;
    this.repository = repository;
    this.viewScheduler = viewScheduler;
  }

  @DebugLog
  @Override public void bindView(UserDetailsContract.View view) {
    this.view = view;

    fetchUserDetails();
  }

  @DebugLog
  @Override public void unbindView() {
    if (subscription != null) {
      subscription.cancel();
    }
  }

  @DebugLog
  private void fetchUserDetails() {
    view.showProgress();

    this.subscription = repository.getUserDetails(userId, new UserRepository.Listener<UserDetails>() {
      @Override public void onReady(final UserDetails details) {
        viewScheduler.execute(new Runnable() {
          @Override public void run() {
            view.hideProgress();
            view.renderUserDetails(details);
          }
        });
      }
    });
  }
}
