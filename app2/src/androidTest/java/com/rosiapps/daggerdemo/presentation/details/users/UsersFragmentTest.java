package com.rosiapps.daggerdemo.presentation.details.users;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.rosiapps.daggerdemo.R;
import com.rosiapps.daggerdemo.data.User;
import com.rosiapps.daggerdemo.presentation.details.DetailsActivity;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserPresenter;
import com.rosiapps.daggerdemo.presentation.details.users.user.UserViewHolder;
import com.rosiapps.daggerdemo.utils.Injector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rosiapps.daggerdemo.tests.matchers.RecyclerViewMatchers.withRecyclerView;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by Shmulik on 03/08/2017.
 * .
 */
@RunWith(AndroidJUnit4.class)
public class UsersFragmentTest {

    @Rule
    public ActivityTestRule<DetailsActivity> activityRule =
            new ActivityTestRule<>(DetailsActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void users_should_be_displayed() {
        UsersContract.Presenter presenter = Injection.TestUsersModule.providePresenter();
        final ArgumentCaptor<UsersContract.View> viewArgumentCaptor = ArgumentCaptor.forClass(UsersContract.View.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                UsersContract.View view = viewArgumentCaptor.getValue();
                List<UserPresenter> list = new ArrayList<>();
                list.add(new UserPresenter(new User("1", "Yossi", "Choen", "yosi@test.com", "")));
                list.add(new UserPresenter(new User("1", "Rafi", "Israel", "rafi@test.com", "")));
                view.renderUsers(list);
                return null;
            }
        }).when(presenter).bindView(viewArgumentCaptor.capture());

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.recycler)).check(matches(isDisplayed()));

        onView(withRecyclerView(R.id.recycler).atPosition(0))
                .check(matches(hasDescendant(withText("Yossi Choen"))));

        onView(withRecyclerView(R.id.recycler).atPosition(1))
                .check(matches(hasDescendant(withText("Rafi Israel"))));
    }

    public interface Injection {

        @Subcomponent(modules = TestUsersModule.class)
        interface TestUsersComponent extends UsersComponent, Injector<UserViewHolder> {

            @Override
            void doInject(UserViewHolder viewHolder);

            @Subcomponent.Builder
            abstract class Builder extends AndroidInjector.Builder<UsersFragment> {
            }
        }

        @Module
        class TestUsersModule {

            private static UsersContract.Presenter presenter = Mockito.mock(UsersContract.Presenter.class);

            @Provides
            static Injector<UserViewHolder> userViewHolderInjector(TestUsersComponent component) {
                return component;
            }

            @Provides
            static UsersContract.Presenter providePresenter() {
                return presenter;
            }
        }

        @Module(subcomponents = {TestUsersComponent.class})
        abstract class TestUsersBindingModule {

            @Binds
            @IntoMap
            @dagger.android.support.FragmentKey(UsersFragment.class)
            abstract AndroidInjector.Factory<? extends Fragment> bindUserDetailsFragmentInjectorFactory(TestUsersComponent.Builder builder);
        }
    }
}