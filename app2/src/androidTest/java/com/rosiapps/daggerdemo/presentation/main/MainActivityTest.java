package com.rosiapps.daggerdemo.presentation.main;

import android.content.Intent;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.TestApplication;
import com.rosiapps.daggerdemo.data.User;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.doAnswer;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private MainActivityInjector injector;

    @Before
    public void setUp() throws Exception
    {
        injector = new MainActivityInjector(TestApplication.getApplication());
    }

    @Test
    public void user_details_should_be_displayed()
    {
        final CountingIdlingResource countingIdlingResource = new CountingIdlingResource("user_details_should_be_displayed");
        countingIdlingResource.increment();
        final User mockUser = new User("0", "mock first", "mock last", "mock@mock.com", "");
        final ArgumentCaptor<MainContract.View> viewArgumentCaptor = ArgumentCaptor.forClass(MainContract.View.class);

        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                MainContract.View view = viewArgumentCaptor.getValue();
                view.renderUserInfo(mockUser);
                return null;
            }
        }).when(injector.presenter).bindView(viewArgumentCaptor.capture());

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.txt_firstname)).check(matches(withText(mockUser.firstName)));
        onView(withId(R.id.txt_lastname)).check(matches(withText(mockUser.lastName)));
        onView(withId(R.id.txt_email)).check(matches(withText(mockUser.email)));
    }
}