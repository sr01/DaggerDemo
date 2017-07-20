package com.rosiapps.daggerdemo.data;

import java.util.List;

public interface UserRepository {
  Subscription getUser(Listener<User> listener);

  Subscription getUserDetails(String userId, Listener<UserDetails> listener);

  Subscription getUserLocations(String userId, Listener<List<Location>> locations);

  interface Listener<T> {
    void onReady(T data);
  }

  interface Subscription {
    void cancel();
  }
}
