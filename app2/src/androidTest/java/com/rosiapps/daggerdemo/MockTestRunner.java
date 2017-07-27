package com.rosiapps.daggerdemo;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by shmulik on 27/07/2017.
 * .
 */
public class MockTestRunner extends AndroidJUnitRunner
{

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}
