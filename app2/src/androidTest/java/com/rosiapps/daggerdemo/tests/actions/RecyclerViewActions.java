package com.rosiapps.daggerdemo.tests.actions;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by shmulik on 18/07/2017.
 * .
 */
public class RecyclerViewActions
{
    /**
     * Usage Example:
     *
     *  onView(withId(R.id.rv_conference_list)).perform(
     *          RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id. bt_deliver)));
     */
    public static ViewAction clickChildViewWithId(final int id)
    {
        return new ViewAction()
        {
            @Override
            public Matcher<View> getConstraints()
            {
                return null;
            }

            @Override
            public String getDescription()
            {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view)
            {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
