package com.rosiapps.daggerdemo;

import android.app.Activity;

import dagger.android.AndroidInjector;

/**
 * Created by shmulik on 03/08/2017.
 * .
 */
public abstract class MockActivityInjector<T extends Activity> implements AndroidInjector<Activity>
{
    private AndroidInjector<Activity> realAndroidInjector;

    public MockActivityInjector(HasMockActivityInjector app)
    {
        realAndroidInjector = app.getRealActivityInjector();
        app.setMockActivityInjector(this);
    }

    @Override
    public void inject(Activity instance)
    {
        T activity = (T) instance;
        //inject activity by the default injector (we provide via component, module, etc.)
        injectDefaults(activity);

        //inject this test mocks dependencies
        injectMocks(activity);
    }

    public abstract void injectMocks(T activity);

    protected void injectDefaults(T activity)
    {
        realAndroidInjector.inject(activity);
    }
}
