package com.rosiapps.daggerdemo.data;

import java.util.List;

public interface UserRepository {

    Subscription getUsers(Listener<List<User>> listener);

    Subscription getUser(String userId, Listener<User> listener);

    Subscription getUserDetails(String userId, Listener<UserDetails> listener);

    interface Listener<T> {
        void onReady(T data);
    }

    interface Subscription {
        void cancel();
    }
}
