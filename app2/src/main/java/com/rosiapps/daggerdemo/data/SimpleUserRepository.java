package com.rosiapps.daggerdemo.data;

import android.util.Log;

import com.rosiapps.daggerdemo.utils.Func0;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleUserRepository implements UserRepository {
  private ScheduledExecutorService executor;

  public SimpleUserRepository() {
    executor = Executors.newSingleThreadScheduledExecutor();
  }

  @Override public Subscription getUser(Listener<User> listener) {
    SubscriptionWork<User> work = new SubscriptionWork<>(listener, new Func0<User>() {
      @Override public User call() {
        return new User("0001", "Ploni", "Almoni", "ploni@ghost.com", "http://www.irishtimes.com/polopoly_fs/1.3103126.1496249528!/image/image.jpg_gen/derivatives/box_620_330/image.jpg");
      }
    });

    executor.schedule(work, 4, TimeUnit.SECONDS);

    return work;
  }

  @Override public Subscription getUserDetails(String userId, Listener<UserDetails> listener) {
    SubscriptionWork<UserDetails> work = new SubscriptionWork<>(listener, new Func0<UserDetails>() {
      @Override public UserDetails call() {
        return new UserDetails("972-54-2152454", "Golan 10 Tel Aviv", "Fight, Coding");
      }
    });

    executor.schedule(work, 4, TimeUnit.SECONDS);

    return work;
  }

  @Override public Subscription getUserLocations(String userId, Listener<List<Location>> listener) {
    SubscriptionWork<List<Location>> work = new SubscriptionWork<>(listener, new Func0<List<Location>>() {
      @Override public List<Location> call() {
        List<Location> list = new ArrayList<>();
        long t = 1121715000;
        list.add(new Location(45.4431641, -121.7295456, t + 1));
        list.add(new Location(45.4428615, -121.7290800, t + 2));
        list.add(new Location(45.4425697, -121.7279085, t + 3));
        list.add(new Location(45.4424274, -121.7267360, t + 4));
        list.add(new Location(45.4422017, -121.7260429, t + 5));
        list.add(new Location(45.4416576, -121.7252347, t + 6));
        list.add(new Location(45.4406144, -121.7241181, t + 7));
        list.add(new Location(45.4398193, -121.7224890, t + 8));
        list.add(new Location(45.4387649, -121.7226112, t + 9));
        list.add(new Location(45.4383933, -121.7224328, t + 10));
        list.add(new Location(45.4377850, -121.7224159, t + 11));
        list.add(new Location(45.4372204, -121.7226603, t + 12));
        list.add(new Location(45.4347837, -121.7226007, t + 13));
        list.add(new Location(45.4332000, -121.7216480, t + 14));
        list.add(new Location(45.4334576, -121.7223143, t + 15));
        list.add(new Location(45.4321730, -121.7222102, t + 16));
        list.add(new Location(45.4316609, -121.7219974, t + 17));
        list.add(new Location(45.4303068, -121.7220616, t + 18));
        list.add(new Location(45.4270753, -121.7209685, t + 19));
        list.add(new Location(45.4267610, -121.7211872, t + 20));
        list.add(new Location(45.4260133, -121.7212623, t + 21));
        list.add(new Location(45.4257683, -121.7214738, t + 22));
        list.add(new Location(45.4257400, -121.7217762, t + 23));
        list.add(new Location(45.4259485, -121.7226009, t + 24));
        list.add(new Location(45.4249972, -121.7223672, t + 25));
        list.add(new Location(45.4246035, -121.7219816, t + 26));
        list.add(new Location(45.4238682, -121.7219830, t + 27));
        list.add(new Location(45.4226721, -121.7216494, t + 28));
        list.add(new Location(45.4224120, -121.7217998, t + 29));
        list.add(new Location(45.4211497, -121.7218767, t + 30));
        list.add(new Location(45.4193319, -121.7208650, t + 31));
        list.add(new Location(45.4186435, -121.7202956, t + 32));
        list.add(new Location(45.4185934, -121.7200745, t + 33));
        list.add(new Location(45.4178963, -121.7196035, t + 34));
        list.add(new Location(45.4171101, -121.7198115, t + 35));
        list.add(new Location(45.4166827, -121.7193250, t + 36));
        list.add(new Location(45.4161855, -121.7190778, t + 37));
        list.add(new Location(45.4159291, -121.7193146, t + 38));
        list.add(new Location(45.4153644, -121.7193939, t + 39));
        list.add(new Location(45.4151268, -121.7191578, t + 40));
        list.add(new Location(45.4148071, -121.7191043, t + 41));
        list.add(new Location(45.4146310, -121.7187962, t + 42));
        list.add(new Location(45.4142524, -121.7187236, t + 43));
        list.add(new Location(45.4142844, -121.7185595, t + 44));
        list.add(new Location(45.4133520, -121.7180429, t + 45));
        list.add(new Location(45.4131406, -121.7181383, t + 46));
        list.add(new Location(45.4130356, -121.7179036, t + 47));
        list.add(new Location(45.4118436, -121.7168789, t + 48));
        list.add(new Location(45.4109205, -121.7156569, t + 49));
        list.add(new Location(45.4104523, -121.7145250, t + 50));
        list.add(new Location(45.4104930, -121.7143814, t + 51));
        list.add(new Location(45.4102075, -121.7140608, t + 52));
        list.add(new Location(45.4099806, -121.7134527, t + 53));
        list.add(new Location(45.4099792, -121.7134610, t + 54));
        list.add(new Location(45.4091489, -121.7134937, t + 55));
        list.add(new Location(45.4086133, -121.7132504, t + 56));
        list.add(new Location(45.4080616, -121.7127670, t + 57));
        list.add(new Location(45.4076426, -121.7126047, t + 58));
        list.add(new Location(45.4075043, -121.7122301, t + 59));
        list.add(new Location(45.4070652, -121.7118980, t + 60));
        list.add(new Location(45.4068712, -121.7114766, t + 61));
        list.add(new Location(45.4067987, -121.7108634, t + 62));
        list.add(new Location(45.4064528, -121.7106934, t + 63));
        list.add(new Location(45.4057286, -121.7110326, t + 64));
        list.add(new Location(45.4056813, -121.7108280, t + 65));
        list.add(new Location(45.4055566, -121.7109216, t + 66));
        list.add(new Location(45.4047244, -121.7093884, t + 67));
        list.add(new Location(45.4039059, -121.7083824, t + 68));
        list.add(new Location(45.4037176, -121.7077738, t + 69));
        list.add(new Location(45.4034533, -121.7074489, t + 70));
        list.add(new Location(45.4026499, -121.7071945, t + 71));
        list.add(new Location(45.4019737, -121.7067004, t + 72));
        list.add(new Location(45.4018086, -121.7067477, t + 73));
        list.add(new Location(45.4014084, -121.7063918, t + 74));
        list.add(new Location(45.4013177, -121.7059701, t + 75));
        list.add(new Location(45.4011965, -121.7058914, t + 76));
        list.add(new Location(45.4010688, -121.7053257, t + 77));
        list.add(new Location(45.4008116, -121.7054978, t + 78));
        list.add(new Location(45.4006075, -121.7053495, t + 79));
        list.add(new Location(45.4005546, -121.7054856, t + 80));
        list.add(new Location(45.3991622, -121.7049765, t + 81));
        list.add(new Location(45.3985560, -121.7042976, t + 82));
        list.add(new Location(45.3981831, -121.7042260, t + 83));
        list.add(new Location(45.3973151, -121.7036992, t + 84));
        list.add(new Location(45.3967974, -121.7036370, t + 85));
        list.add(new Location(45.3963985, -121.7033742, t + 86));
        list.add(new Location(45.3945456, -121.7029688, t + 87));
        list.add(new Location(45.3920595, -121.7015918, t + 88));
        list.add(new Location(45.3907614, -121.7012029, t + 89));
        list.add(new Location(45.3906454, -121.7010483, t + 90));
        list.add(new Location(45.3906726, -121.7008185, t + 91));
        list.add(new Location(45.3909774, -121.7008263, t + 92));
        list.add(new Location(45.3911315, -121.7004300, t + 93));
        list.add(new Location(45.3909963, -121.6998193, t + 94));
        list.add(new Location(45.3908688, -121.6997923, t + 95));
        list.add(new Location(45.3917895, -121.6994679, t + 96));
        list.add(new Location(45.3926205, -121.6994847, t + 97));
        list.add(new Location(45.3925915, -121.6992485, t + 98));
        list.add(new Location(45.3928117, -121.6995661, t + 99));
        return list;
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

    @Override public void cancel() {
      isCanceled.set(true);
    }

    @Override public void run() {
      if (isCanceled.get()) {
        Log.d(TAG, "subscription canceled, abort execution");
        return;
      }

      listener.onReady(dataProvider.call());
    }
  }
}
