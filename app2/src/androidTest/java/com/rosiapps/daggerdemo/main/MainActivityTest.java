package com.rosiapps.daggerdemo.main;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.TestAppComponent;
import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.data.UserRepository;
import com.rosiapps.daggerdemo.presentation.main.MainActivity;
import com.rosiapps.daggerdemo.utils.HasComponent;

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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Inject
    UserRepository mUserRepository;
    private ScheduledExecutorService mExecutor = Executors.newSingleThreadScheduledExecutor();

    @Before
    public void setUp() throws Exception
    {
        Context applicationContext = InstrumentationRegistry.getTargetContext().getApplicationContext();
        HasComponent<TestAppComponent> hasComponent = (HasComponent<TestAppComponent>) applicationContext;
        TestAppComponent component = hasComponent.getComponent();
        component.inject(this);
    }

    @Test
    public void user_details_should_be_displayed()
    {
        //TODO: S.R - this test should not test the presenter, it should test the UI and therefor should mock the presenter.
        
        final CountingIdlingResource countingIdlingResource = new CountingIdlingResource("user_details_should_be_displayed");
        countingIdlingResource.increment();
        Espresso.registerIdlingResources(countingIdlingResource);

        final User mockUser = new User("0", "mock first", "mock last", "mock@mock.com", "");
        final UserRepository.Subscription subscription = Mockito.mock(UserRepository.Subscription.class);
        final ArgumentCaptor<UserRepository.Listener<User>> listenerCap = ArgumentCaptor.forClass(UserRepository.Listener.class);
        when(mUserRepository.getUser(anyString(), listenerCap.capture())).then(new Answer<UserRepository.Subscription>()
        {
            @Override
            public UserRepository.Subscription answer(InvocationOnMock invocation) throws Throwable
            {
                mExecutor.schedule(new Runnable()
                {
                    @Override
                    public void run()
                    {
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