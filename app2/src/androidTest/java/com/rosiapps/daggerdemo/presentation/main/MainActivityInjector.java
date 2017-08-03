package com.rosiapps.daggerdemo.presentation.main;

import com.rosiapps.daggerdemo.HasMockActivityInjector;
import com.rosiapps.daggerdemo.MockActivityInjector;

import org.mockito.Mockito;

/**
 * Created by shmulik on 03/08/2017.
 * .
 */
class MainActivityInjector extends MockActivityInjector<MainActivity>
{
    MainContract.Presenter presenter;

    MainActivityInjector(HasMockActivityInjector app)
    {
        super(app);
        presenter = Mockito.mock(MainContract.Presenter.class);
    }

    @Override
    public void injectMocks(MainActivity activity)
    {
        activity.presenter = presenter;
    }
}
