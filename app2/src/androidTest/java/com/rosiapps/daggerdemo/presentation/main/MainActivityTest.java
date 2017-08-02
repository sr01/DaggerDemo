package com.rosiapps.daggerdemo.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.TestApplication;
import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);
    @Inject
    UserRepository mUserRepository;
    private MainContract.Presenter mPresenter;
    private ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();

    @Before
    public void setUp() throws Exception {
        mPresenter = Mockito.mock(MainContract.Presenter.class);

        TestApplication app = (TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        mUserRepository = app.userRepository;
        final DispatchingAndroidInjector<Activity> dispatchingActivityInjector = app.getDispatchingActivityInjector();
        app.setActivityAndroidInjector(new AndroidInjector<Activity>() {
            @Override
            public void inject(Activity instance) {
                MainActivity activity = (MainActivity) instance;
                dispatchingActivityInjector.inject(activity);
                activity.presenter = mPresenter;
            }
        });
    }

    @Test
    public void user_details_should_be_displayed() {
        //TODO: S.R - this test should not test the mPresenter, it should test the UI and therefor should mock the mPresenter.

        final CountingIdlingResource countingIdlingResource = new CountingIdlingResource("user_details_should_be_displayed");
        countingIdlingResource.increment();
        Espresso.registerIdlingResources(countingIdlingResource);

        final User mockUser = new User("0", "mock first", "mock last", "mock@mock.com", "");
        final UserRepository.Subscription subscription = Mockito.mock(UserRepository.Subscription.class);
        final ArgumentCaptor<UserRepository.Listener<User>> listenerCap = ArgumentCaptor.forClass(UserRepository.Listener.class);
        when(mUserRepository.getUser(anyString(), listenerCap.capture())).then(new Answer<UserRepository.Subscription>() {
            @Override
            public UserRepository.Subscription answer(InvocationOnMock invocation) throws Throwable {
                mExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        UserRepository.Listener<User> listener = listenerCap.getValue();
                        listener.onReady(mockUser);
                        countingIdlingResource.decrement();
                    }
                }, 2, TimeUnit.SECONDS);

                return subscription;
            }
        });

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.txt_firstname)).check(matches(withText(mockUser.firstName)));
        onView(withId(R.id.txt_lastname)).check(matches(withText(mockUser.lastName)));
        onView(withId(R.id.txt_email)).check(matches(withText(mockUser.email)));

        Espresso.unregisterIdlingResources(countingIdlingResource);
    }
}