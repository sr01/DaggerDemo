package com.rosiapps.daggerdemo.data;

public interface UserRepository {
  Subscription getUser(Listener listener);

  interface Listener {
    void onUserReady(User userInfo);
  }

  interface Subscription {
    void cancel();
  }
}
