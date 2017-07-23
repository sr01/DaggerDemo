package com.rosiapps.daggerdemo.presentation.main;

import android.util.Log;

import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.utils.Scheduler;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";
    private final String userId;
    private MainContract.View view;
    private UserRepository repository;
    private UserRepository.Subscription subscription;
    private Scheduler viewScheduler;
    private MainNavigator navigator;

    @DebugLog
    @Inject
    public MainPresenter(String userId, UserRepository repository, Scheduler viewScheduler, MainNavigator navigator) {
        this.userId = userId;
        this.repository = repository;
        this.viewScheduler = viewScheduler;
        this.navigator = navigator;
    }

    @DebugLog
    @Override
    public void bindView(MainContract.View view) {
        this.view = view;

        fetchUserInfo();
    }

    @DebugLog
    @Override
    public void unbindView() {
        if (subscription != null) {
            subscription.cancel();
        }
    }

    @Override
    public void showMore() {
        navigator.gotoUsers();
    }

    @DebugLog
    private void fetchUserInfo() {
        view.showProgress();

        this.subscription = repository.getUser(userId, new UserRepository.Listener<User>() {
            @Override
            public void onReady(final User user) {
                viewScheduler.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "get user, onReady, user: " + user);
                        view.hideProgress();
                        view.renderUserInfo(user);
                    }
                });
            }
        });
    }
}
