package com.rosiapps.daggerdemo.presentation.details.users;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserPresenter;
import com.rosiapps.daggerdemo.utils.Scheduler;

import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;

public class UsersPresenter implements UsersContract.Presenter {
    private UsersContract.View view;
    private UserRepository repository;
    private UserRepository.Subscription subscription;
    private Scheduler viewScheduler;

    @DebugLog
    @Inject
    public UsersPresenter(UserRepository repository, Scheduler viewScheduler) {
        this.repository = repository;
        this.viewScheduler = viewScheduler;
    }

    @DebugLog
    @Override
    public void bindView(UsersContract.View view) {
        this.view = view;

        fetchUserDetails();
    }

    @DebugLog
    @Override
    public void unbindView() {
        if (subscription != null) {
            subscription.cancel();
        }
    }

    @DebugLog
    private void fetchUserDetails() {
        view.showProgress();

        this.subscription = repository.getUsers(new UserRepository.Listener<List<User>>() {
            @Override
            public void onReady(final List<User> users) {
                viewScheduler.execute(new Runnable() {
                    @Override
                    public void run() {
                        view.hideProgress();
                        view.renderUsers(usersToPresenters(users));
                    }
                });
            }
        });
    }

    private List<UserPresenter> usersToPresenters(List<User> users) {
        return Stream.of(users).map(new Function<User, UserPresenter>() {
            @Override
            public UserPresenter apply(User user) {
                return new UserPresenter(user);
            }
        }).toList();
    }
}
