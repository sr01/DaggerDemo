package com.rosiapps.daggerdemo.data;

import android.content.Context;
import android.util.Log;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.annimon.stream.function.Predicate;
import com.rosiapps.daggerdemo.data.store.Result;
import com.rosiapps.daggerdemo.data.store.UsersStore;
import com.rosiapps.daggerdemo.utils.Func0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

public class SimpleUserRepository implements UserRepository {
    private ScheduledExecutorService executor;
    private UsersStore usersStore;

    @Inject
    public SimpleUserRepository(Context context) {
        try {
            usersStore = UsersStore.create(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        executor = Executors.newSingleThreadScheduledExecutor();
    }

    private static User resultToUser(Result result) {
        return new User(result.getId().toString(),
                result.getName().getFirst(),
                result.getName().getLast(),
                result.getEmail(),
                result.getPicture().getMedium());
    }

    private static UserDetails resultToUserDetails(Result result) {
        return new UserDetails(result.getPhone(),
                result.getLocation().getStreet() + ", " +
                        result.getLocation().getCity() + ", " +
                        result.getLocation().getState(),
                result.getNat());
    }

    @Override
    public Subscription getUsers(Listener<List<User>> listener) {
        SubscriptionWork<List<User>> work = new SubscriptionWork<>(listener, new Func0<List<User>>() {
            @Override
            public List<User> call() {
                return Stream.of(usersStore.getResults())
                        .map(new Function<Result, User>() {
                            @Override
                            public User apply(Result result) {
                                return resultToUser(result);
                            }
                        }).toList();
            }
        });

        executor.schedule(work, 4, TimeUnit.SECONDS);

        return work;
    }

    @Override
    public Subscription getUser(final String userId, Listener<User> listener) {
        SubscriptionWork<User> work = new SubscriptionWork<>(listener, new Func0<User>() {
            @Override
            public User call() {
                return Stream.of(usersStore.getResults())
                        .filter(new Predicate<Result>() {
                            @Override
                            public boolean test(Result value) {
                                return value.getId().getValue().equalsIgnoreCase(userId);
                            }
                        }).findFirst()
                        .map(new Function<Result, User>() {
                            @Override
                            public User apply(Result result) {
                                return resultToUser(result);
                            }
                        }).orElse(null);
            }
        });
        executor.schedule(work, 4, TimeUnit.SECONDS);
        return work;
    }

    @Override
    public Subscription getUserDetails(final String userId, Listener<UserDetails> listener) {
        SubscriptionWork<UserDetails> work = new SubscriptionWork<>(listener, new Func0<UserDetails>() {
            @Override
            public UserDetails call() {
                return Stream.of(usersStore.getResults())
                        .filter(new Predicate<Result>() {
                            @Override
                            public boolean test(Result value) {
                                return value.getId().toString().equalsIgnoreCase(userId);
                            }
                        }).findFirst()
                        .map(new Function<Result, UserDetails>() {
                            @Override
                            public UserDetails apply(Result result) {
                                return resultToUserDetails(result);
                            }
                        }).orElse(null);
            }
        });

        executor.schedule(work, 4, TimeUnit.SECONDS);

        return work;
    }

    private static class SubscriptionWork<T> implements Runnable, Subscription {
        private static final String TAG = "SubscriptionWork";
        private AtomicBoolean isCanceled = new AtomicBoolean(false);
        private Listener<T> listener;
        private Func0<T> dataProvider;

        SubscriptionWork(Listener<T> listener, Func0<T> dataProvider) {
            this.listener = listener;
            this.dataProvider = dataProvider;
        }

        @Override
        public void cancel() {
            isCanceled.set(true);
        }

        @Override
        public void run() {
            if (isCanceled.get()) {
                Log.d(TAG, "subscription canceled, abort execution");
                return;
            }

            listener.onReady(dataProvider.call());
        }
    }
}
