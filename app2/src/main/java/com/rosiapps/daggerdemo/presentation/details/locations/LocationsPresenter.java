package com.rosiapps.daggerdemo.presentation.details.locations;

import com.rosiapps.daggerdemo.data.Location;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class LocationsPresenter implements LocationsContract.Presenter {
  private LocationsContract.View view;
  private UserRepository repository;
  private UserRepository.Subscription subscription;
  private Scheduler viewScheduler;
  private String userId;

  @DebugLog
  @Inject
  public LocationsPresenter(String userId, UserRepository repository, Scheduler viewScheduler) {
    this.userId = userId;
    this.repository = repository;
    this.viewScheduler = viewScheduler;
  }

  @DebugLog
  @Override public void bindView(LocationsContract.View view) {
    this.view = view;

    fetchLocations();
  }

  @DebugLog
  @Override public void unbindView() {
    if (subscription != null) {
      subscription.cancel();
    }
  }

  @DebugLog
  private void fetchLocations() {
    view.showProgress();

    this.subscription = repository.getUserLocations(userId, new UserRepository.Listener<List<Location>>() {
      @Override public void onReady(final List<Location> locations) {
        viewScheduler.execute(new Runnable() {
          @Override public void run() {
            view.hideProgress();
            view.renderLocations(locations);
          }
        });
      }
    });
  }
}
