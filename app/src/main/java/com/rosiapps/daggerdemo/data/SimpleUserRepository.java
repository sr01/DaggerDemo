package com.rosiapps.daggerdemo.data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleUserRepository implements UserRepository {
  private ScheduledExecutorService executor;

  public SimpleUserRepository() {
    executor = Executors.newSingleThreadScheduledExecutor();
  }

  @Override public Subscription getUser(Listener listener) {
    SubscriptionWork work = new SubscriptionWork(listener);

    executor.schedule(work, 5, TimeUnit.SECONDS);

    return work;
  }

  private static class SubscriptionWork implements Runnable, Subscription {
    private AtomicBoolean isCanceled = new AtomicBoolean(false);
    private Listener listener;
    private User user;

    SubscriptionWork(Listener listener) {
      this.listener = listener;
      this.user = new User("Ploni", "Almoni", "ploni@ghost.com", "http://www.irishtimes.com/polopoly_fs/1.3103126.1496249528!/image/image.jpg_gen/derivatives/box_620_330/image.jpg");
    }

    @Override public void cancel() {
      isCanceled.set(true);
    }

    @Override public void run() {
      if (isCanceled.get()) {
        return;
      }

      listener.onUserReady(user);
    }
  }
}
