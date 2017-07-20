package com.rosiapps.daggerdemo.utils;

import android.os.Handler;
import android.os.Looper;

public final class Schedulers {
  private static Scheduler androidMainScheduler = new Scheduler() {
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override public void execute(Runnable runnable) {
      handler.post(runnable);
    }
  };

  public static Scheduler androidMainScheduler() {
    return androidMainScheduler;
  }
}
